import { useState } from 'react';

function UserActivityTab() {
  const [curTab, setTab] = useState(0);
  const menuArr = [
    { name: '등록한 게시글', content: '등록한 게시글입니다.' },
    { name: '등록한 댓글', content: <Comment /> },
  ];
  const selectMenuHandler = idx => {
    setTab(idx);
  };

  return (
    <div>
      {menuArr.map((tap, idx) => {
        return (
          <li key={idx} onClick={() => selectMenuHandler(idx)}>
            {curTab.name}
          </li>
        );
      })}
    </div>
  );
}

export default UserActivityTab;
