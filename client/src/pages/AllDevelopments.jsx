import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import Aside from '../components/layout/Aside';
import Card from '../components/UI/Card';
import Item from '../components/Item';
import { fetchAllDevelopmentsAction } from '../store/developmentSlice';
import { ChevronDownIcon, PencilIcon } from '../components/Icons';

function AllDevelopments() {
  const [sortaActive, setSortaActive] = useState('전체');
  const { allDevelopments } = useSelector(state => state.developments);
  const dispatch = useDispatch();

  const onSortaButtonClickHandler = e => setSortaActive(prev => e.target.value);
  // const sortaButtonStyle = sortaActive;
  // const sortaButtonStyle = buttonValue =>
  //   `py-[0.375rem] px-4 border-[1px] border-solid border-gray8  rounded-2xl ${
  //     sortaActive === buttonValue ? 'bg-black3 text-white1' : 'bg-white1 text-black3'
  //   }`;
  const activeStyle = 'bg-black3 text-white1';
  const notActiveStyle = 'bg-white1 text-black3';

  useEffect(() => {
    dispatch(fetchAllDevelopmentsAction());
  }, []);

  return (
    <>
      <Aside
        title="Tech Stack"
        categories={{
          titles: [
            'javascript',
            'typescript',
            'Authentication',
            'Webpack',
            'Css-in-js',
            'React',
            'Vue',
            'Angular',
            'Nextjs',
            'Database',
            'Network',
            'Algorithm',
          ],
        }}
      />
      <div className="pt-main-top w-full">
        <div className="flex justify-between mt-3">
          <div className="flex gap-2">
            <button
              onClick={onSortaButtonClickHandler}
              type="button"
              className={`text-xs px-3 border-[1px] border-solid border-gray8 rounded-2xl ${
                sortaActive === '전체' ? activeStyle : notActiveStyle
              }`}
              value="전체"
            >
              전체
            </button>
            <button
              onClick={onSortaButtonClickHandler}
              type="button"
              className={`text-xs px-3 border-[1px] border-solid border-gray8 rounded-2xl ${
                sortaActive === '글' ? activeStyle : notActiveStyle
              }`}
              value="글"
            >
              글
            </button>
            <button
              onClick={onSortaButtonClickHandler}
              type="button"
              className={`text-xs px-3 border-[1px] border-solid border-gray8 rounded-2xl ${
                sortaActive === '영상' ? activeStyle : notActiveStyle
              }`}
              value="영상"
            >
              영상
            </button>
            <button
              onClick={onSortaButtonClickHandler}
              type="button"
              className={`text-xs px-3 border-[1px] border-solid border-gray8 rounded-2xl ${
                sortaActive === '트렌드' ? activeStyle : notActiveStyle
              }`}
              value="트렌드"
            >
              트렌드
            </button>
          </div>
          <div className="flex items-center border-[1px] border-gray6 border-solid p-2 text-[0.625rem] cursor-pointer">
            <span className="mr-2">최신순</span> <ChevronDownIcon />
          </div>
        </div>
        <div className="w-full flex flex-col max-w-limit">
          <h3 className="flex items-center text-[1.2rem] py-7 mt-5 border-b-[1px] border-solid border-gray4">
            <PencilIcon className="w-5 h-5 mr-3" />{' '}
            <span className="font-bold">글 쓰기</span>
          </h3>
          <div className="w-full flex justify-between flex-wrap gap-4 py-14">
            {allDevelopments.data.map(info => (
              <Card key={info.postId} width="30%">
                <Item {...info} />
              </Card>
            ))}
          </div>
        </div>
      </div>
    </>
  );
}

export default AllDevelopments;
