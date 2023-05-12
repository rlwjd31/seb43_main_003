import { useEffect } from 'react';

const autoSlideInterval = (effectCallback, delay) => {
  const intervalId = setInterval(effectCallback, delay);

  return intervalId;
};

function useAutoSlide(effectCallback, delay) {
  useEffect(() => {
    const intervalId = autoSlideInterval(effectCallback, delay);

    return () => clearInterval(intervalId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);
}

export default useAutoSlide;
