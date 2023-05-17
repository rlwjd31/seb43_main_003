const { USER_DATA } = require("../../db/data");
// JWT는 verifyToken으로 검증할 수 있습니다. 먼저 tokenFunctions에 작성된 여러 메서드들의 역할을 파악하세요.
const { verifyToken, generateToken } = require("../helper/tokenFunctions");

module.exports = async (req, res) => {
  //페이로드의 user id로 할건데, verifyToken으로 페이로드를 반환받아서
  //쿠키에서 받아오기
  const cookies = req.cookies;
  //{access_jwt : token, refresh_jwt : token} 으로 받아옴

  //페이로드(id, email) 가져오기 -> 페이로드나 null 이 들어있을 예정
  const accessPayload = verifyToken("access", cookies.access_jwt);
  const refreshPayload = verifyToken("refresh", cookies.refresh_jwt);

  //두 토큰이 모두 만료된 경우
  if (!accessPayload && !refreshPayload) {
    console.log("not here");
    return res.status(401).send("Not Authorized");
  }
  //access토큰이 만료된 경우
  if (!accessPayload && refreshPayload) {
    //accessToken 재 발급 한 것을 쿠키에 넣어서 보내줌
    const accessTokenCookieOption = {
      domain: "localhost",
      path: "/",
      httpOnly: true,
    };
    const userInfo = {
      ...USER_DATA.filter((user) => user.id === refreshPayload.id)[0],
    };
    const result = generateToken(userInfo, false);
    res.cookie("access_jwt", result.accessToken, accessTokenCookieOption);
    delete userInfo.password;
    //민감한 정보는 지워서 보내준다.
    return res.send(userInfo);
  }
  //access token에 대한 검증 성공 -> userInfo 찾아서 응답으로 보내준다.
  const userInfo = {
    ...USER_DATA.filter((user) => user.id === accessPayload.id)[0],
  };
  delete userInfo.password;
  // 민감한 정보는 지워서 보내준다.
  return res.send(userInfo);

  /*
   * TODO: 토큰 검증 여부에 따라 유저 정보를 전달하는 로직을 구현하세요.
   *
   * Access Token에 대한 검증이 성공하면 복호화된 payload를 이용하여 USER_DATA에서 해당하는 유저를 조회할 수 있습니다.
   * Access Token이 만료되었다면 Refresh Token을 검증해 Access Token을 재발급하여야 합니다.
   * Access Token과 Refresh Token 모두 만료되었다면 상태 코드 401을 보내야합니다.
   */
};
