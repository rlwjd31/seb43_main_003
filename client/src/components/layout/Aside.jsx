import { useState } from 'react';
import { NavLink } from 'react-router-dom';

function Aside({ title, categories }) {
  const [isActive, setIsActive] = useState(0);

  const profileNavActiveStyle = ({ isActive }) =>
    `text-[14px] font-normal cursor-pointer ${isActive && 'text-activeBlue'}`;

  const onActiveClickHandler = activeIndex => setIsActive(prev => activeIndex);

  return (
    <div className="mt-main-top w-[13rem] mr-[4.125rem]">
      <div className="w-[7.3rem] border-b-[3px] border-solid border-black3 font-bold text-xl text-black3 pb-[15px] mb-[2rem]">
        {title}
      </div>
      <div>
        {categories?.titles?.map((title, index) => {
          return (
            // eslint-disable-next-line react/no-array-index-key
            <div key={index} className="flex flex-col items-start py-[15px]">
              {categories?.path && (
                <NavLink to={categories?.path[index]} className={profileNavActiveStyle}>
                  {title}
                </NavLink>
              )}
              {/* 전체개발페이지처럼 filter기능이라 보내줄 path가 없을 시 */}
              {!categories?.path && (
                // eslint-disable-next-line jsx-a11y/no-static-element-interactions
                <div
                  onClick={() => onActiveClickHandler(index)}
                  className={profileNavActiveStyle({ isActive: index === isActive })}
                >
                  {title}
                </div>
              )}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Aside;
