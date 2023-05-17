import { Tab, Tabs } from '../components/UI/UserActiveTabs';

function UserActivity() {
  return (
    <div className="w-screen bg-gray1 flex flex-col items-center">
      <div className="max-w-limit flex justify-center">
        <Aside
          title={'마이페이지'}
          categories={{
            titles: ['내 프로필', '내 활동'],
            path: [`${BASE_URL}/user`, `${BASE_URL}/user/activities`],
          }}
        />
        <Tabs>
          <Tab label="Tab 1">
            <p className="my-[27px] text-black3 text-[15px] font-medium leading-4">
              총 3개{' '}
            </p>
            <div className="flex justify-between"></div>
          </Tab>
        </Tabs>
      </div>
    </div>
  );
}

export default UserActivity;
