import { useSelector } from 'react-redux';

import Header from '../layout/Header';
import Carousel from '../Carousel';
import Footer from '../layout/Footer';

function Home() {
  const { infos } = useSelector(state => state.developInfos);

  return (
    <div className="w-screen flex flex-col items-center bg-gray1">
      <Header />
      <div className="max-w-limit pt-[180px]">
        <Carousel />
        {infos.map(info => {
          const {
            id,
            title,
            source,
            imageURI,
            recommendedAvg,
            recommends,
            author,
            sorta,
            sourceUrI,
          } = info;
          return (
            <div key={id}>
              <div>{title}</div>
              <div>{source}</div>
              <img src={imageURI} alt="썸네일 및 대표 사진" className="h-48 w-48" />
              <div>{recommendedAvg}</div>
              <div>{recommends}</div>
              <div>{author}</div>
              <div>{sorta}</div>
              <div>{sourceUrI}</div>
            </div>
          );
        })}
      </div>
      <Footer />
    </div>
  );
}

export default Home;
