import { useState } from 'react';
import CarouselImg1 from '../assets/carousel-1.png';
import CarouselImg2 from '../assets/carousel-2.png';
import CarouselImg3 from '../assets/carousel-3.png';
import useAutoSlide from '../hooks/useAutoSlide';

const carouselImgs = [
  CarouselImg3,
  CarouselImg1,
  CarouselImg2,
  CarouselImg3,
  CarouselImg1,
];

function Carousel() {
  const transitionDelay = 3000;
  const transitionStyle = 'transition-all ease-in-out duration-500';
  const [currentIdx, setCurrentIdx] = useState(0);
  const [isTransition, setIsTransition] = useState(true);

  const onSliderClickHandler = direction => {
    setCurrentIdx(prev =>
      direction === 'next'
        ? (prev + 1) % carouselImgs.length
        : (prev + carouselImgs.length - 1) % carouselImgs.length,
    );
  };

  useAutoSlide(() => onSliderClickHandler('next'), transitionDelay);

  const onEllipsisClickHandler = index => setCurrentIdx(index);

  const replaceSlide = () => {
    setIsTransition(() => {
      setTimeout(() => {
        setIsTransition(true);
      }, 100);
      return false;
    });
    setCurrentIdx(prev => {
      if (prev === 4) return 1;
      if (prev === 0) return 3;

      return prev;
    });
  };

  const onSlideTransitionEndHandler = () => {
    if (currentIdx === 4 || currentIdx === 0) replaceSlide();
  };

  return (
    <div className="relative w-full overflow-hidden">
      <div
        onTransitionEnd={onSlideTransitionEndHandler}
        style={{ transform: `translateX(-${currentIdx * 100}%)` }}
        className={`flex w-full ${isTransition ? transitionStyle : ''}`}
      >
        {carouselImgs.map((imgSource, index) => (
          <img
            // eslint-disable-next-line react/no-array-index-key
            key={index}
            src={imgSource}
            alt={`carousel-img-${index + 1}`}
          />
        ))}
      </div>
      <div className="absolute bottom-6 left-[45%] flex w-full">
        <button
          type="button"
          onClick={() => onSliderClickHandler('prev')}
          className="relative rounded-full w-[30px] h-[30px] cursor-pointer hover:bg-[#E8E8E8] border-solid border-[1px] border-gray8 mr-2"
        >
          <span className="absolute top-2 left-2">&larr;</span>
        </button>
        <div className="flex items-center gap-2 mr-2">
          {Array(carouselImgs.length - 2)
            .fill(0)
            .map((_, index) => (
              <div
                // eslint-disable-next-line react/no-array-index-key
                key={index}
                aria-label="CarouselPagination"
                role="button"
                onClick={() => onEllipsisClickHandler(index + 1)}
                className={`w-[0.5rem] h-[0.5rem] ${
                  currentIdx === index + 1 ? 'bg-[#FFFFFF]' : 'bg-gray10'
                } rounded-full cursor-pointer`}
              />
            ))}
        </div>
        <button
          type="button"
          onClick={() => onSliderClickHandler('next')}
          className="relative rounded-full w-[30px] h-[30px] cursor-pointer hover:bg-[#E8E8E8] border-solid border-[1px] border-gray8"
        >
          <span className="absolute top-2 left-2">&rarr;</span>
        </button>
      </div>
    </div>
  );
}

export default Carousel;
