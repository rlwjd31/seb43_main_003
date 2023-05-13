import { GithubIcon, GoogleIcon, KakaoIcon } from '../components/Icons.jsx';

function Login() {
  return (
    <div className="my-[9.6rem] flex justify-center items-center pt-[180px]">
      <div className="w-[33.5rem] h-[31.5rem] px-[3rem] flex-col">
        <p className="text-[30px] text-black3 font-bold text-center">로그인</p>
        <form className="mt-[3.75rem] py-[15px] flex-col">
          <input
            type="email"
            className="bg-white w-full h-[3.5rem] px-[1.3rem] py-[0.5rem]"
            placeholder="아이디"
          />
          <input
            type="password"
            className="mt-[15px] bg-white w-full h-[3.5rem] px-[1.3rem] py-[0.5rem]"
            placeholder="비밀번호"
          />
          <button
            type="submit"
            className="mt-[32px] w-full h-[3.5rem] bg-black3 text-gray1 text-[14px] font-bold text-center"
          >
            로그인하기
          </button>
        </form>
        <p className="text-right font-bold text-[12px] text-gray11 cursor-pointer">
          비밀번호 찾기
        </p>
        <div className="w-full flex justify-between mt-[2.7rem] px-[8.5rem] ">
          <button className="w-[2.6rem] h-[2.6rem] rounded-full bg-black flex justify-center items-center">
            <GithubIcon className="w-[2rem] h-[2rem]" />
          </button>
          <button className="w-[2.6rem] h-[2.6rem] rounded-full bg-white flex justify-center items-center">
            <GoogleIcon className="w-[2rem] h-[2rem]" />
          </button>
          <button className="w-[2.6rem] h-[2.6rem] rounded-full bg-kakaoYellow flex justify-center items-center">
            <KakaoIcon className="w-[2rem] h-[2rem]" />
          </button>
        </div>
        <div className="my-[26px] mx-[12.5rem] border-t-[1px] border-solid border-gray8"></div>
        <div className="flex justify-center">
          <p className="text-black3 text-[12px] font-bold pr-[5px]">
            아직 계정이 없으신가요?
          </p>
          <p className="text-activeBlue text-[12px] font-bold">회원가입</p>
        </div>
      </div>
    </div>
  );
}

export default Login;
