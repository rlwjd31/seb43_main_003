require("dotenv").config();
const { sign, verify } = require("jsonwebtoken");

module.exports = {
  //token을 만들어주는 메서드 generateToken
  generateToken: (user, checkedKeepLogin) => {
    //token에서 payload가 실제로 들어갈 데이터, 시그니처는 헤더랑 페이로드가 조작인지 판단!
    //데이터에 들어갈 형태 만들어줌
    const payload = {
      id: user.id,
      email: user.email,
    };

    //sign이 토큰을 실제로 만들어줌
    console.log("ACCESS_SECRET", process.env.ACCESS_SECRET);
    let result = {
      // TODO: SECRET_KEY 넣어주기
      accessToken: sign(payload, process.env.ACCESS_SECRET, {
        expiresIn: "1d", // 1일간 유효한 토큰을 발행합니다.
      }),
    };
    //result 객체 안에 accessToken 키의 값으로 token을 만들어줌
    //result에 토큰을 담아서 리턴을 할건데, 토큰이 access, refresh 두개!!

    //체크가 되어 있을 때 refresh를 만들어주는거
    //체크가 되어있따. === 로그인이 유지되어야 한다.
    //access는 세션쿠기라 브라우저를 닫으면 없어진다. 유지하고 싶다면  refresh토큰을 사용 => persistent 쿠키(유효기간이 설정된 쿠키)
    if (checkedKeepLogin) {
      result.refreshToken = sign(payload, process.env.REFRESH_SECRET, {
        expiresIn: "7d", // 일주일간 유효한 토큰을 발행합니다.
      });
    }
    return result;
  },
  //토큰의 타입을 받고, 토큰도 받아 각 타입에 맞는 시크릿키를 가져와서
  //페이로드나 널을 리턴하는 함수 (객체라서 verifyToken ':' -> 메소드를 만든 것 => 객체에 함수를 만드는게 메소드)
  //유효성 확인 메소드
  verifyToken: (type, token) => {
    let secretKey, decoded;
    switch (type) {
      case "access":
        secretKey = process.env.ACCESS_SECRET;
        break;
      case "refresh":
        secretKey = process.env.REFRESH_SECRET;
        break;
      default:
        return null;
    }
    //가져온키를 유효한지 확인
    try {
      //통과되면 페이로드가 decoded에 담길 것이다.
      decoded = verify(token, secretKey);
    } catch (err) {
      console.log(`JWT Error: ${err.message}`);
      return null;
    }
    return decoded;
  },
};
