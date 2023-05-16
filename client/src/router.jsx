import { createBrowserRouter } from 'react-router-dom';
import Home from './pages/Home';
import PageNotFound from './pages/PageNotFound';
import Profile from './pages/Profile';

const routers = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
    errorElement: <PageNotFound />,
  },
  {
    path: '/user',
    element: <Profile />,
  },
]);

export default routers;
