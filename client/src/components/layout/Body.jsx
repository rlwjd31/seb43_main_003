import axios from '../../utils/axios';
import { useEffect } from 'react';

import Footer from './Footer';
import Header from './Header';

function Body({ children, layoutInfo }) {
  const { isMainContentWidthScreen } = layoutInfo;

  useEffect(() => {
    const fetchAllDevelopmentsData = async () => {
      try {
        const response = await axios.get(
          'http://ec2-3-34-183-170.ap-northeast-2.compute.amazonaws.com:8080/posts',
        );
        console.log('^^^^^^^^^^^^^^^^^^^^^^^^^^^^');
        console.log('all development datas 👇');
        console.log(response.data);
        console.log('response.status', response.status);
        // if (response.status >= 200 && response.status < 300) {
        // }
      } catch (error) {
        console.log(error.message);
      }
    };

    const fetchUserInfo = async () => {
      try {
        const response = await axios.get('userinfo');

        if (response.status >= 200 && response.status < 300) {
          console.log(`from server /userinfo 👉🏻`, response.data);
        }
      } catch (err) {
        console.log(err.message);
      }
    };

    fetchAllDevelopmentsData();
    fetchUserInfo();
  }, []);

  return (
    <div className="w-screen flex flex-col items-center bg-gray1 font-noto-kr">
      <Header />
      {isMainContentWidthScreen && children}

      {!isMainContentWidthScreen && (
        <main className="max-w-limit w-full pt-36 flex justify-center">{children}</main>
      )}
      <Footer />
    </div>
  );
}

export default Body;
