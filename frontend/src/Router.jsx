// 라우터 관련 객체
// 작성자: 김준섭

import { createBrowserRouter } from 'react-router-dom';
// import ErrorComponent from './components/ErrorComponent';
import PATH from './constants/path';
import PAGE from './constants/page';
import App from './App';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: PATH.Course,
        element: <PAGE.Course />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.Signup,
        element: <PAGE.Signup />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.CourseCreate,
        element: <PAGE.CourseCreate />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.CourseDetail,
        element: <PAGE.CourseDetail />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.CourseUpdate,
        element: <PAGE.CourseUpdate />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.MyPage,
        element: <PAGE.MyPage />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.Place,
        element: <PAGE.Place />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.Login,
        element: <PAGE.Login />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.PwFind,
        element: <PAGE.PasswordFind />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.GoogleCallBack,
        element: <PAGE.GoogleLoginCallback />,
        errorElement: <PAGE.NotFound />,
      },
      {
        path: PATH.KakaoCallBack,
        element: <PAGE.KakaoLoginCallback />,
        errorElement: <PAGE.NotFound />,
      },
    ],
    errorElement: <PAGE.NotFound />,
  },
]);

export default router;
