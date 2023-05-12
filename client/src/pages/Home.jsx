import { useSelector } from 'react-redux';

import Header from '../components/layout/Header';
import Carousel from '../components/Carousel';
import Footer from '../components/layout/Footer';
import Card from '../components/UI/Card';
import Item from '../components/Item';

function Home() {
  const { infos } = useSelector(state => state.developInfos);

  return (
    <div className="w-screen flex flex-col items-center bg-gray1">
      <Header />
      <div className="max-w-limit pt-[180px]">
        <Carousel />
        <div className="flex w-full justify-between">
          {infos.map(info => (
            <Card key={info.id} width="32%">
              <Item {...info} />
            </Card>
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Home;
