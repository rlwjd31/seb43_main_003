import Footer from './Footer';
import Header from './Header';

function Body({ children, layoutInfo }) {
  const { isMainContentWidthScreen } = layoutInfo;

  return (
    <div className="w-screen flex flex-col items-center bg-gray1 font-noto-kr">
      <Header />
      {isMainContentWidthScreen && children}
      {!isMainContentWidthScreen && children}
      <Footer />
    </div>
  );
}

export default Body;
