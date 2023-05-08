import { ReactComponent as Search } from './assets/search.svg';
import { ReactComponent as User } from './assets/user.svg';

const validateClassName = className => {
  return typeof className === 'string' && className.length > 0 ? { className } : '';
};

export function SearchIcon({ className }) {
  return <Search {...validateClassName(className)} />;
}

export function UserIcon({ className }) {
  return <User {...validateClassName(className)} />;
}

export default {};
