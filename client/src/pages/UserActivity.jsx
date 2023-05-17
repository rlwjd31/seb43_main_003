import { useSelector } from 'react-redux';
import { Tabs, Tab } from '../components/UI/ActiviteisTab';
import Aside from '../components/layout/Aside';
import Card from '../components/UI/Card';
import Item from '../components/Item';
import Comment from '../components/UI/Comment';

const BASE_URL = 'http://localhost:5173';

function UserActivity() {
  const { infos } = useSelector(state => state.developInfos);

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
        <div>
          <p className="mb-[10px] font-bold text-[18px] text-black3">내 게시물</p>
          <Tabs>
            <Tab label="등록한 게시글">
              <p className="my-[27px] text-black3 text-[15px] font-medium leading-4">
                총 3개
              </p>
              <div className="flex justify-between">
                {infos.map(info => (
                  <Card key={info.id} width={'30%'}>
                    <Item {...info} />
                  </Card>
                ))}
              </div>
            </Tab>
            <Tab label="등록한 댓글">
              <p className="my-[27px] text-black3 text-[15px] font-medium leading-4">
                총 3개
              </p>
              <div className="flex flex-col">
                <Comment />
                <Comment />
                <Comment />
              </div>
            </Tab>
          </Tabs>
        </div>
      </div>
    </div>
  );
}

export default UserActivity;
