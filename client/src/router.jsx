import { createBrowserRouter } from 'react-router-dom';
import Home from './pages/Home';
import PageNotFound from './pages/PageNotFound';

const routers = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
    errorElement: <PageNotFound />,
  },
]);

export default routers;
