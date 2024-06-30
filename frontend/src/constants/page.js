// URL 별로 할당된 페이지 컴포넌트들 매핑 집합
// 작성자: 김준섭

import Course from '../pages/course/Course';
import Signup from '../pages/signup/Signup';
import CourseCreate from '../pages/course-create/CourseCreate';
import CourseDetail from '../pages/course-detail/CourseDetail';
import CourseUpdate from '../pages/course-update/CourseUpdate';
import MyPage from '../pages/mypage/MyPage';
import Place from '../pages/place/Place';
import NotFound from '../pages/error/NotFound';
import Login from '../pages/login/Login';
import PasswordFind from '../pages/password-find/PasswordFind';
import GoogleLoginCallback from '../components/login/GoogleCallBack';
import KakaoLoginCallback from '../components/login/KakaoCallBack';

const PAGE = {
  Course,
  Signup,
  CourseCreate,
  CourseDetail,
  CourseUpdate,
  MyPage,
  Place,
  NotFound,
  Login,
  PasswordFind,
  GoogleLoginCallback,
  KakaoLoginCallback,
};

export default PAGE;
