import { useState } from 'react';
import axios from 'axios';

import { GithubIcon, GoogleIcon, KakaoIcon } from '../components/Icons';

const instanceAxios = axios.create({
  withCredentials: true,
});

const fetchLogin = async loginInfo => {
  const URL = 'http://localhost:4000/login';
  const body = loginInfo;
  console.log('body', body);
  try {
    const response = await instanceAxios.post(URL, body);
    return response.data;
  } catch (err) {
    console.log(`error: ${err.message}`);
  }

  return null;
};

function Login() {
  const [loginInfo, setLoginInfo] = useState({
    userId: '',
    password: '',
  });

  const onLoginSubmitHandler = e => {
    e.preventDefault();
    const userData = fetchLogin(loginInfo);
    console.log('response.data ->', userData);
  };

  const onEmailChangeHandler = e =>
    setLoginInfo(prev => ({ ...prev, userId: e.target.value }));

  const onPasswordChangeHandler = e =>
    setLoginInfo(prev => ({ ...prev, password: e.target.value }));

  return (
    <div className="my-[9.6rem] flex justify-center items-center pt-[180px]">
      <div className="w-[33.5rem] h-[31.5rem] px-[3rem] flex-col">
        <p className="text-[30px] text-black3 font-bold text-center">로그인</p>
        <form onSubmit={onLoginSubmitHandler} className="mt-[3.75rem] py-[15px] flex-col">
          <input
            onChange={onEmailChangeHandler}
            type="text"
            className="bg-white w-full h-[3.5rem] px-[1.3rem] py-[0.5rem]"
            placeholder="이메일"
            required
          />
          <input
            onChange={onPasswordChangeHandler}
            type="password"
            className="mt-[15px] bg-white w-full h-[3.5rem] px-[1.3rem] py-[0.5rem]"
            placeholder="비밀번호"
            required
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
          <button
            type="button"
            className="w-[2.6rem] h-[2.6rem] rounded-full bg-black flex justify-center items-center"
          >
            <GithubIcon className="w-[2rem] h-[2rem]" />
          </button>
          <button
            type="button"
            className="w-[2.6rem] h-[2.6rem] rounded-full bg-white flex justify-center items-center"
          >
            <GoogleIcon className="w-[2rem] h-[2rem]" />
          </button>
          <button
            type="button"
            className="w-[2.6rem] h-[2.6rem] rounded-full bg-kakaoYellow flex justify-center items-center"
          >
            <KakaoIcon className="w-[2rem] h-[2rem]" />
          </button>
        </div>
        <div className="my-[26px] mx-[12.5rem] border-t-[1px] border-solid border-gray8" />
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
