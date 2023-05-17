import {useState} from "react";

function Tabs({children}) {
  const [activeTab, setActiveTab] = useState(children[0].props.label);
  const handleTabClick = (e, newActiveTab) => {
    e.preventDefault();
    setActiveTab(newActiveTab)
  }

  return (
    <div>
      <div>
        {children.map(child) => (
          <button key={child.props.label} className={`${activeTab === child.props.label ? 'font-medium' : ''} text-[15px] text-black3 leading-4`} 
          onClick={e => handleTabClick(e, child.props.label)}
        )}
      </div>
    </div>
  );
}

export default Tabs;
