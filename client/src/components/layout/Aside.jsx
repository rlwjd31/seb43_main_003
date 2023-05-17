import { NavLink } from 'react-router-dom';

function Aside({ title, categories }) {
  const profileNavActiveStyle = ({ isActive }) =>
    `text-[17px] font-normal ${isActive && 'text-activeBlue'}`;

  return (
    <div className="mt-[282px] w-[13rem] mr-[4.125rem]">
      <div className="w-[7.3rem] border-b-[3px] border-solid border-black3 font-bold text-[25px] text-black3 pb-[15px] mb-[2rem]">
        {title}
      </div>
      <div>
        {categories?.titles?.map((title, index) => {
          return (
            <div className="flex flex-col items-start py-[15px]">
              <NavLink to={categories?.path[index]} className={profileNavActiveStyle}>
                {title}
              </NavLink>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Aside;
