import { useState } from 'react';

function Tabs({ children }) {
  const [activeTab, setActiveTab] = useState(children[0].props.label);
  const handleTabClick = (e, newActiveTab) => {
    e.preventDefault();
    setActiveTab(newActiveTab);
  };

  return (
    <div>
      <div className="w-[50rem] py-[18px] border-solid border-b-[1px] border-gray4">
        {children.map(child => (
          <button
            key={child.props.label}
            className={`${
              activeTab === child.props.label ? 'text-black3' : 'text-gray6'
            } text-[15px] font-medium leading-4`}
            onClick={e => handleTabClick(e, child.props.label)}
          >
            {child.props.label}
          </button>
        ))}
      </div>
      <div>
        {children.map(child => {
          if (child.props.label === activeTab) {
            return <div key={child.props.label}>{child.props.children}</div>;
          }
          return null;
        })}
      </div>
    </div>
  );
}

function Tab({ label, children }) {
  return (
    <div label={label} className="hidden">
      {children}
    </div>
  );
}

export { Tabs, Tab };
