import { useNavigate, useLocation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';

import navPlace from '/src/assets/icons/nav-place.png';
import navHome from '/src/assets/icons/nav-home.png';
import navMypage from '/src/assets/icons/nav-mypage.png';

import * as S from './Header.style';
import arrowLeft from '/src/assets/icons/header-arrow-left.png';
import arrowRight from '/src/assets/icons/header-arrow-right.png';

function Header() {
  const navigate = useNavigate();
  const goPlace = () => navigate('/place');
  const goCourse = () => navigate('/');
  const goMypage = () => navigate('/mypage');

  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  const location = useLocation();
  const currentUrl = location.pathname;

  const goPrev = () => {
    if (currentUrl !== '/coursecreate') navigate('/');
    else if (currentUrl == '/coursecreate') {
      if (currPage == 0) navigate('/');
      else if (currPage == 1) setCurrPage(0);
      else setCurrPage(1);
    }
  };
  const goPost = () => {
    if (currentUrl == '/coursecreate') {
      if (currPage == 0) setCurrPage(1);
      else if (currPage == 1) setCurrPage(2);
      else console.log('create Course');
    }
  };

  return (
    <S.Container>
      <S.SectionImg onClick={goPlace} src={navPlace} style={{ width: '40px', height: '40px', margin: '0 0 3px 10px' }} />
      <S.SectionImg onClick={goCourse} src={navHome} />
      <S.SectionImg onClick={goMypage} src={navMypage} style={{ width: '60px', height: '60px' }} />
    </S.Container>
  );
}

export default Header;
