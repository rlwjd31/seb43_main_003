import { useState } from 'react';
import CarouselImg1 from './assets/carousel-1.png';
import CarouselImg2 from './assets/carousel-2.png';
import CarouselImg3 from './assets/carousel-3.png';

function Carousel() {
  const [currentIdx, setCurrentIdx] = useState(0);

  const carouselImgs = [CarouselImg1, CarouselImg2, CarouselImg3];

  const onPrevClickHandler = () =>
    setCurrentIdx(prev => (prev === 0 ? carouselImgs.length - 1 : prev - 1));

  const onNextClickHandler = () =>
    setCurrentIdx(prev => (prev === carouselImgs.length - 1 ? 0 : prev + 1));

  return (
    <div className="relative w-full h-[560px] overflow-hidden">
      <div
        className={`flex w-full transition-all ease-in-out duration-500 translate-x-[-${
          currentIdx * 100
        }%]`}
      >
        {carouselImgs.map((imgSource, index) => (
          <img
            // eslint-disable-next-line react/no-array-index-key
            key={new Date().getTime() + index}
            src={imgSource}
            className="object-fill object-center"
            alt={`carousel-img-${index + 1}`}
          />
        ))}
      </div>
      {/* <img src={CarouselImg2} className="object-fill object-center" alt="coursel-img-2" />
      <img src={CarouselImg2} className="object-fill object-center" alt="coursel-img-1" /> */}
      <div className="absolute bottom-10 left-[45%] flex w-full">
        <button
          type="button"
          onClick={onPrevClickHandler}
          className="relative rounded-full w-[30px] h-[30px] cursor-pointer hover:bg-[#E8E8E8] border-solid border-[1px] border-gray8 mr-2"
        >
          <span className="absolute top-2 left-2">&larr;</span>
        </button>
        <div className="flex items-center gap-2 mr-2">
          <div className="w-[6px] h-[6px] bg-gray10 rounded-full cursor-pointer" />
          <div className="w-[6px] h-[6px] bg-gray10 rounded-full cursor-pointer" />
          <div className="w-[6px] h-[6px] bg-gray10 rounded-full cursor-pointer" />
        </div>
        <button
          type="button"
          onClick={onNextClickHandler}
          className="relative rounded-full w-[30px] h-[30px] cursor-pointer hover:bg-[#E8E8E8] border-solid border-[1px] border-gray8"
        >
          <span className="absolute top-2 left-2">&rarr;</span>
        </button>
      </div>
    </div>
  );
}

export default Carousel;
