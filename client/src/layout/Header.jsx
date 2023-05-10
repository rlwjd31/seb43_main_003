import { NavLink, Link } from 'react-router-dom';
import { useRef } from 'react';

import { UserIcon, SearchIcon } from '../Icons';

function Header() {
  const inputRef = useRef(null);
  const applyNavLinkActivedStyle = ({ isActive }) =>
    `p-[10px] pb-[15px] tracking-[.15rem] ${isActive && 'text-[#0045F6]'}`;

  const onFormClickHandler = () => {
    inputRef.current.focus();
  };

  return (
    <div className="fixed top-0 flex justify-center h-[180px] w-screen pt-8 bg-gray1 font-play text-[0.75rem] border-solid border-b-[1px] border-gray7/30">
      <div className="flex flex-col w-full max-w-limit">
        <Link to="user/login" className="flex justify-end pr-[10px]">
          <UserIcon className="h-[12px] w-[12px] mr-2" />
          <span>LOGIN</span>
        </Link>
        <div className="flex items-end">
          <h1 className="text-[28px] font-[900] font-noto-kr pr-14">
            <div className="mb-[20px]">HELLO,</div>
            <div className="relative top-[-5px]">DEVELOPER!</div>
          </h1>
          <form
            onClick={onFormClickHandler}
            className="group flex w-full justify-between h-[40px] items-center bg-[#FFFFFF] py-3 px-5 mr-8 shadow-around rounded-3xl"
          >
            <SearchIcon className="h-[14px] w-[14px] mr-2" />
            <input
              ref={inputRef}
              className="w-full bg-transparent outline-none border-0 focus:ring-0"
              type="text"
              placeholder="Search"
            />
          </form>
          <nav className="flex">
            <NavLink to="/" className={applyNavLinkActivedStyle}>
              HOME
            </NavLink>
            <NavLink to="/about" className={applyNavLinkActivedStyle}>
              ABOUT
            </NavLink>
            <NavLink to="/development" className={applyNavLinkActivedStyle}>
              DEVELOPMENT
            </NavLink>
            <NavLink to="/equipment" className={applyNavLinkActivedStyle}>
              EQUIPMENT
            </NavLink>
          </nav>
        </div>
      </div>
    </div>
  );
}

export default Header;
