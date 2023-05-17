import { createBrowserRouter } from 'react-router-dom';

import Body from './components/layout/Body';
import Home from './pages/Home';
import Login from './pages/Login';
import PageNotFound from './pages/PageNotFound';

const routerData = [
  {
    path: '/',
    element: <Home />,
    errorElement: <PageNotFound />,
    layoutInfo: {
      isHeader: true,
      isMainContentWidthScreen: true,
      isFooter: true,
    },
  },
  {
    path: '/user/login',
    element: <Login />,
    layoutInfo: {
      isHeader: true,
      isMainContentWidthScreen: true,
      isFooter: true,
    },
  },
];

const routers = createBrowserRouter(
  routerData.reduce((accRouterArr, currentRouterInfo) => {
    const parsedRouter = {
      ...currentRouterInfo,
      element: (
        <Body layoutInfo={currentRouterInfo?.layoutInfo}>
          {currentRouterInfo.element}
        </Body>
      ),
    };

    delete parsedRouter.layoutInfo;

    return [...accRouterArr, parsedRouter];
  }, []),
);

export default routers;
