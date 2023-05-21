// import { useEffect, useState } from 'react';
// import { useDispatch, useSelector } from 'react-redux';
// import { Link } from 'react-router-dom';

// import Aside from '../components/layout/Aside';
// import Card from '../components/UI/Card';
// import Item from '../components/Item';
// import Button from '../components/UI/Button';
// import Accordian from '../components/UI/Accordian';
// import { fetchAllDevelopmentsAction } from '../store/developmentSlice';
// import { ChevronDownIcon, PencilIcon } from '../components/Icons';

// function AllDevelopments() {
//   const [filterValue, setFilterValue] = useState({
//     techStack: null,
//     sorta: null,
//     best: null,
//   });
//   const [sortaActive, setSortaActive] = useState('전체');
//   const [accordianVisible, setAccordianVisible] = useState(false);
//   const [accordianFilterValue, setAccordianFilterValue] = useState('최신순');
//   const { allDevelopments } = useSelector(state => state.developments);
//   const dispatch = useDispatch();

//   const onSortaButtonClickHandler = e => setSortaActive(prev => e.target.value);

//   const onAccordianFilterVisibleClickHandler = () => setAccordianVisible(prev => !prev);

//   const onAccordianFilterValueClickHandler = filterValue => {
//     setAccordianFilterValue(prev => filterValue);
//     // accordian에서 필터 값을 클릭한다면 accordian접기
//     setAccordianVisible(prev => false);
//   };

//   const activeStyle = 'bg-black3 text-white1';
//   const notActiveStyle = 'bg-white1 text-black3';

//   useEffect(() => {
//     dispatch(fetchAllDevelopmentsAction());
//   }, []);

//   return (
//     <>
//       <section>
//         <Aside
//           title="Tech Stack"
//           categories={{
//             titles: [
//               'all',
//               'javascript',
//               'typescript',
//               'Authentication',
//               'Webpack',
//               'Css-in-js',
//               'React',
//               'Vue',
//               'Angular',
//               'Nextjs',
//               'Database',
//               'Network',
//               'Algorithm',
//             ],
//           }}
//         />
//       </section>
//       <section className="w-full pt-28">
//         <div className="sticky top-36 flex flex-col pt-8 bg-gray1">
//           <div className="relative flex justify-between">
//             <div className="flex gap-2">
//               {['전체', '글', '영상', '트렌드'].map((filterValue, index) => (
//                 <Button
//                   // eslint-disable-next-line react/no-array-index-key
//                   key={index}
//                   value={filterValue}
//                   onClick={e => onSortaButtonClickHandler(e)}
//                   className={`text-xs px-3 py-1 border-[1px] border-solid border-gray8 rounded-2xl ${
//                     sortaActive === filterValue ? activeStyle : notActiveStyle
//                   }`}
//                 >
//                   {filterValue}
//                 </Button>
//               ))}
//             </div>
//             <div className="absolute top-0 right-0 flex flex-col border-[1px] border-gray6 border-solid text-[0.625rem] cursor-pointer">
//               <Button onClick={onAccordianFilterVisibleClickHandler}>
//                 <span className="p-2">{accordianFilterValue}</span>{' '}
//                 <ChevronDownIcon className="mr-2" />
//               </Button>
//               <Accordian
//                 onClickHandler={onAccordianFilterValueClickHandler}
//                 activeValue={accordianFilterValue}
//                 itemList={['최신순', '인기순']}
//                 className="border-t-[1px] p-2 border-gray6 border-solid"
//                 activeColor="text-activeBlue"
//                 visible={accordianVisible}
//               />
//             </div>
//           </div>
//           <h3 className="flex items-center text-[1.2rem] mt-5 border-b-[1px] border-solid border-gray4">
//             <Link to="/developments/new" className="flex w-32 py-7 pr-4">
//               <PencilIcon className="w-5 h-5 mr-3" />{' '}
//               <span className="font-bold">글 쓰기</span>
//             </Link>
//           </h3>
//         </div>
//         <div className="w-full flex flex-col max-w-limit">
//           <div className="w-full flex justify-between flex-wrap gap-6 py-8">
//             {allDevelopments.data.map(info => (
//               <Card key={info.postId} flexItemwidth="30%">
//                 <Item {...info} />
//               </Card>
//             ))}
//           </div>
//         </div>
//       </section>
//     </>
//   );
// }

// export default AllDevelopments;

import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

import Aside from '../components/layout/Aside';
import Card from '../components/UI/Card';
import Item from '../components/Item';
import Button from '../components/UI/Button';
import Accordian from '../components/UI/Accordian';
import { fetchAllDevelopmentsAction } from '../store/developmentSlice';
import { ChevronDownIcon, PencilIcon } from '../components/Icons';

function AllDevelopments() {
  const { allDevelopments } = useSelector(state => state.developments);
  const dispatch = useDispatch();
  const [filterValue, setFilterValue] = useState({
    tag: 'All',
    sorta: 'All',
    best: '최신순',
  });
  const [accordianVisible, setAccordianVisible] = useState(false);

  const onSortaButtonClickHandler = e =>
    setFilterValue(prev => ({ ...prev, sorta: e.target.value }));

  const onAccordianFilterVisibleClickHandler = () => setAccordianVisible(prev => !prev);

  const onBestFilterClickHandler = filterValue => {
    setFilterValue(prev => ({ ...prev, best: filterValue }));
    // accordian에서 필터 값을 클릭한다면 accordian접기
    setAccordianVisible(prev => false);
  };

  const activeStyle = 'bg-black3 text-white1';
  const notActiveStyle = 'bg-white1 text-black3';

  useEffect(() => {
    dispatch(fetchAllDevelopmentsAction());
  }, []);

  const filteredDevelopments = allDevelopments.data
    .filter(developmentInfo =>
      filterValue.tag === 'All'
        ? true
        : developmentInfo.tags[0].tags
            .map(v => v.toLowerCase())
            .includes(filterValue.tag.toLowerCase()),
    )
    .filter(developmentInfo =>
      filterValue.sorta === 'All'
        ? true
        : developmentInfo.sorta.toLowerCase() === filterValue.sorta.toLowerCase(),
    );
  // TODO: sort로직 구현해야 됨.

  return (
    <>
      <section>
        <Aside
          title="Tech Stack"
          categories={{
            titles: [
              'All',
              'Js',
              'Ts',
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
          activeValue={filterValue.tag}
          onFilterClickHandler={activeValue =>
            setFilterValue(prev => ({ ...prev, tag: activeValue }))
          }
        />
      </section>
      <section className="w-full pt-28">
        <div className="sticky top-36 flex flex-col pt-8 bg-gray1">
          <div className="relative flex justify-between">
            <div className="flex gap-2">
              {['All', 'Text', 'Video', 'Trend'].map((sortaValue, index) => (
                <Button
                  // eslint-disable-next-line react/no-array-index-key
                  key={index}
                  value={sortaValue}
                  onClick={e => onSortaButtonClickHandler(e)}
                  className={`text-xs px-3 py-1 border-[1px] border-solid border-gray8 rounded-2xl ${
                    filterValue.sorta === sortaValue ? activeStyle : notActiveStyle
                  }`}
                >
                  {sortaValue}
                </Button>
              ))}
            </div>
            <div className="absolute top-0 right-0 flex flex-col border-[1px] border-gray6 border-solid text-[0.625rem] cursor-pointer">
              <Button onClick={onAccordianFilterVisibleClickHandler}>
                <span className="p-2">{filterValue.best}</span>{' '}
                <ChevronDownIcon className="mr-2" />
              </Button>
              <Accordian
                onClickHandler={onBestFilterClickHandler}
                activeValue={filterValue.best}
                itemList={['최신순', '인기순']}
                className="border-t-[1px] p-2 border-gray6 border-solid"
                activeColor="text-activeBlue"
                visible={accordianVisible}
              />
            </div>
          </div>
          <h3 className="flex items-center text-[1.2rem] mt-5 border-b-[1px] border-solid border-gray4">
            <Link to="/developments/new" className="flex w-32 py-7 pr-4">
              <PencilIcon className="w-5 h-5 mr-3" />{' '}
              <span className="font-bold">글 쓰기</span>
            </Link>
          </h3>
        </div>
        <div className="w-full flex flex-col max-w-limit">
          <div className="w-full flex justify-between flex-wrap gap-6 py-8">
            {filteredDevelopments.map(info => (
              <Card key={info.postId} flexItemwidth="30%">
                <Item {...info} />
              </Card>
            ))}
          </div>
        </div>
      </section>
    </>
  );
}

export default AllDevelopments;
