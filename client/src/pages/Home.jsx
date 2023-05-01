import { useSelector } from 'react-redux';

function Home() {
  const { infos } = useSelector(state => state.developInfos);

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
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
  );
}

export default Home;
