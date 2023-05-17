import { ReactComponent as Search } from '../assets/search.svg';
import { ReactComponent as User } from '../assets/user.svg';
import { ReactComponent as Star } from '../assets/star.svg';
import { ReactComponent as Union } from '../assets/union.svg';
import { ReactComponent as GithubLogo } from '../assets/githubLogo.svg';
import { ReactComponent as GoogleLogo } from '../assets/googleLogo.svg';
import { ReactComponent as KakaoLogo } from '../assets/kakaoLogo.svg';

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
  return <GithubLogo {...validateClassName(className)} />;
}

export function GoogleIcon({ className }) {
  return <GoogleLogo {...validateClassName(className)} />;
}

export function KakaoIcon({ className }) {
  return <KakaoLogo {...validateClassName(className)} />;
}

export default {};
