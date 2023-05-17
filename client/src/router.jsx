import { createBrowserRouter } from 'react-router-dom';

import Home from './pages/Home';
import Login from './pages/Login';
import PageNotFound from './pages/PageNotFound';

const routers = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
    errorElement: <PageNotFound />,
  },
  {
    path: '/user/login',
    element: <Login />,
  },
]);

export default routers;
