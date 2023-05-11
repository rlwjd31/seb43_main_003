function Footer() {
  return (
    <div className="w-screen h-[23rem] bg-white flex justify-center">
      <footer className="max-w-limit w-full h-[12rem] flex-col justify-center">
        <div className="h-[11.5rem] flex justify-between items-center ">
          <div className="font-extrabold text-[28px] text-[black1]">
            <p>HELLO,</p>
            <p className="pt-[15px]">DEVELOPER!</p>
          </div>
          <div className="w-[31rem] flex justify-between">
            <ul className="font-bold text-black1 text-[18px]">
              About Us
              <li className="font-medium text-[13px] text-gray6 mt-[30px]">
                프로젝트소개
              </li>
              <li className="font-medium text-[13px] text-gray6 mt-[15px]">팀원소개</li>
            </ul>
            <ul className="font-bold text-black1 text-[18px]">
              My Account
              <li className="font-medium text-[13px] text-gray6 mt-[30px]">SIGN IN</li>
              <li className="font-medium text-[13px] text-gray6 mt-[15px]">
                회원정보수정
              </li>
            </ul>
            <ul className="font-bold text-black1 text-[18px]">
              Help
              <li className="font-medium text-[13px] text-gray6 mt-[30px]">공지사항</li>
              <li className="font-medium text-[13px] text-gray6 mt-[15px]">CONTACT US</li>
            </ul>
          </div>
        </div>
        <div className="font-play max-w-limit w-full pt-[2rem] border-t border-solid border-gray2 font-normal text-[14px] text-black1">
          © 2023 Coding Team. All rights reserved.
        </div>
      </footer>
    </div>
  );
}

export default Footer;
