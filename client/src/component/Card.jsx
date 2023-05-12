import { useSelector } from 'react-redux';

import { ReactComponent as Star } from '../assets/star.svg';
import { ReactComponent as Union } from '../assets/union.svg';

function Card() {
  const { infos } = useSelector(state => state.developInfos);

  return (
    <div key={infos[0].id} className="w-[21.5rem] h-[359px] flex-col items-center">
      <div className="h-[12rem] flex justify-center items-center bg-yellow-100">
        <img src={infos[0].imageURI} alt="썸네일 및 대표 사진" />
      </div>
      <div className="h-[10.5rem] bg-white p-[26px]">
        <div>
          <p className="text-[12px] text-gray4 font-semibold">{infos[0].source}</p>
          <div className="w-[18rem] h-[4.6rem] text-[17px] text-black3 font-bold border-b-[1px] border-solid border-gray2 mt-[13px]">
            {infos[0].title}
          </div>
        </div>
        <div className="flex justify-between items-center mt-[13px]">
          <div className="flex">
            <img
              className="w-[21px] h-[21px] rounded-full"
              src="imageURI"
              alt="유저이미지"
            />
            <p className="text-[12px] text-gray4">{infos[0].author}</p>
          </div>
          <div className="flex">
            <div className="flex">
              <Star className="mr-[9px]" />
              <p className="text-[12px] text-gray4 mr-[15px]">
                {infos[0].recommendedAvg}
              </p>
            </div>
            <div className="flex">
              <Union className="mr-[9px]" />
              <p className="text-[12px] text-gray4">{infos[0].recommends}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Card;
