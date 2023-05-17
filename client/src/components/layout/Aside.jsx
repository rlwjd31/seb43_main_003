import { Link } from 'react-router-dom';

function Aside({ title, categories }) {
  const subNavLinkActiveStyle = ({ isActive }) => {
    isActive
      ? 'text-[17px] font-normal text-activeBlue border-none'
      : 'text-[17px] font-normal text-gray6 border-none';
  };

  // {
  //   titles: ['내 프로필', '내 활동'],
  //   path: [`${BASE_URL}/my-profile`, `${BASE_URL}/my-activies`],
  // }

  return (
    <div className="mt-[282px] w-[13rem] mr-[4.125rem]">
      <div className="w-[7.3rem] border-b-[3px] border-solid border-black3 font-bold text-[25px] text-black3 pb-[15px] mb-[2rem]">
        {title}
      </div>
      <div>
        {categories?.titles?.map((title, index) => {
          console.log(`title: ${title}`);

          return (
            <div className="flex flex-col items-start py-[15px]">
              <Link to={categories?.path[index]}>{title}</Link>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Aside;
