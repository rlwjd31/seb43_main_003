import { ReactComponent as Search } from '../assets/search.svg';
import { ReactComponent as User } from '../assets/user.svg';
import { ReactComponent as Star } from '../assets/star.svg';
import { ReactComponent as Union } from '../assets/union.svg';
import { ReactComponent as githubLogo } from '../assets/githubLogo.svg';
import { ReactComponent as googleLogo } from '../assets/googleLogo.svg';
import { ReactComponent as kakaoLogo } from '../assets/kakaoLogo.svg';

const validateClassName = className => {
  return typeof className === 'string' && className.length > 0 ? { className } : '';
};

export function SearchIcon({ className }) {
  return <Search {...validateClassName(className)} />;
}

export function UserIcon({ className }) {
  return <User {...validateClassName(className)} />;
}

export function StarIcon({ className }) {
  return <Star {...validateClassName(className)} />;
}

export function UnionIcon({ className }) {
  return <Union {...validateClassName(className)} />;
}

export function GithubIcon({ className }) {
  return <githubLogo {...validateClassName(className)} />;
}

export function GoogleIcon({ className }) {
  return <googleLogo {...validateClassName(className)} />;
}

export function KakaoIcon({ className }) {
  return <kakaoLogo {...validateClassName(className)} />;
}

export default {};
