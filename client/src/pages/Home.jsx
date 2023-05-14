import { useSelector } from 'react-redux';

import Header from '../components/layout/Header';
import Carousel from '../components/Carousel';
import Footer from '../components/layout/Footer';
import Card from '../components/UI/Card';
import Item from '../components/Item';

const CaurouselConfig = {
  auto: true,
  infinite: true,
  carouselIntervalTime: 3000,
  transitionDelay: 500,
};

function Home() {
  const { infos } = useSelector(state => state.developInfos);

  return (
    <>
      <Header />
      <div className="w-screen flex flex-col items-center bg-gray1">
        <div className="w-full flex justify-center pt-[160px] bg-gray3">
          <Carousel {...CaurouselConfig} />
        </div>
        <div className="w-full flex justify-center bg-white1">
          <h1>something</h1>
        </div>
        {/* flex flex-col 같이 써주어야 items-center 먹음 */}
        <div className="w-full flex flex-col items-center bg-gray1">
          <div className="flex w-full max-w-limit justify-between">
            {infos.map(info => (
              <Card key={info.id} width="31.5%">
                <Item {...info} />
              </Card>
            ))}
          </div>
          <div className="flex w-full max-w-limit justify-between">
            {infos.map(info => (
              <Card key={info.id} width="31.5%">
                <Item {...info} />
              </Card>
            ))}
          </div>
        </div>
        <Footer />
      </div>
    </>
  );
}

export default Home;
