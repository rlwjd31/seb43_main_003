import ProfileContent from '../components/ProfileContent';
import Aside from '../components/layout/Aside';
import Footer from '../components/layout/Footer';
import Header from '../components/layout/Header';

const BASE_URL = 'http://localhost:5173';

function Profile() {
  return (
    <div className="w-screen bg-gray1 flex flex-col items-center">
      <Header />
      <div className="max-w-limit flex justify-center">
        <Aside
          title={'마이페이지'}
          categories={{
            titles: ['내 프로필', '내 활동'],
            path: [`${BASE_URL}/user`, `${BASE_URL}/user/activities`],
          }}
        />
        <ProfileContent />
      </div>
      <Footer />
    </div>
  );
}

export default Profile;
