const { USER_DATA } = require("../../db/data");
// JWT는 generateToken으로 생성할 수 있습니다. 먼저 tokenFunctions에 작성된 여러 메서드들의 역할을 파악하세요.
const { generateToken } = require("../helper/tokenFunctions");

module.exports = async (req, res) => {
  console.log("req.body", req.body);
  const { userId, password } = req.body;
  const { checkedKeepLogin } = req.body;
  console.log(`userId: ${userId}\tpassword: ${password}`);
  const userInfo = {
    ...USER_DATA.filter(
      (user) => user.userId === userId && user.password === password
    )[0],
  };

  //쿠키 옵션을 설정
  const accessTokenCookieOption = {
    domain: "localhost",
    path: "/",
    httpOnly: true,
  };
  const refreshTokenCookieOption = {
    domain: "localhost",
    path: "/",
    httpOnly: true,
    //유효기간
    maxAge: 1000 * 60 * 60 * 24 * 7,
  };

  console.log("test");
  if (userInfo.id === undefined) {
    console.log(`userInfo: ${userInfo}`);
    console.log("here!");
    return res.status(401).send("Not Authorized");
  } else if (checkedKeepLogin) {
    const { accessToken, refreshToken } = generateToken(userInfo, checkedKeepLogin); // 키 이름을 맞춰줘야 구조분해할당시 값이 잘 들어와요~~~
    // result = {accessToken : token, refreshToken : token}
    console.log("accessToken: ", accessToken);
    console.log("refreshToken: ", refreshToken);
    res.cookie("access_jwt", accessToken, accessTokenCookieOption);
    res.cookie("refresh_jwt", refreshToken, refreshTokenCookieOption);
    res.redirect("/userinfo");
  } else {
    //로그인 유지를 누르지 않은 상태 -> access토큰을 만들어 쿠키에 넣고, /userInfo에 redirect한다.
    //이 else문은 checkedKeepLogin이 false이므로 tokenFunctions의 generateToken value 함수를 확인
    const { accessToken } = generateToken(userInfo, checkedKeepLogin); //result 객체 -> {accessToken : value(===token)} -> accessToken값만 필요하니까 그 값을 구조분해할당으로 만들어줌
    res.cookie("access_jwt", accessToken, accessTokenCookieOption);
    // const result = generateToken(userInfo, checkedKeepLogin);
    // res.cookie("access_jwt", at.accessToken, accessTokenCookieOption)
    //            쿠키이름,     쿠키값,          쿠키옵션
    res.redirect("/userinfo");
    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
  }
  /*
   * TODO: 로그인 로직을 구현하세요.
   *
   * userInfo에는 요청의 바디를 이용해 db에서 조회한 유저정보가 담겨있습니다. 콘솔에서 userInfo를 출력해보세요.
   * 유저의 정보가 출력된다면 해당 유저가 존재하는 것임으로 로그인 성공에 대한 응답을 전송해야합니다.
   * 만약 undefined가 출력된다면 해당하는 유저가 존재하지 않는 것임으로 로그인 실패에 대한 응답을 전송해야합니다.
   *
   * 로그인 성공 시에는 쿠키에 JWT를 담아 전송해야합니다.
   * 로그인 상태가 유지되어야 한다면 Access Token과 Refresh Token 모두 보내야합니다.
   * Access Token은 Session 쿠키로 Refresh Token은 Persistent Cookie로 보내야합니다.
   * Access Token의 쿠키 아이디는 access_jwt, Refresh Token의 쿠키 아이디는 refresh_jwt로 작성하세요.
   *
   * 로그인 상태가 유지되길 원하지 않는다면 Access Token만 보내야합니다.
   *
   * 클라이언트에게 바로 응답을 보내지않고 서버의 /useinfo로 리다이렉트해야 합니다.
   * express의 res.redirect 메서드를 참고하여 서버의 /userinfo로 리다이렉트 될 수 있도록 구현하세요.
   */
};
