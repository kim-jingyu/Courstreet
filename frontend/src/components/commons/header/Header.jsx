// 지도검색, 메인, 마이페이지로 가는 헤더 네이게이터
// 작성자: 김준섭

import { useNavigate, useLocation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';

import navPlace from '/src/assets/icons/nav-place.png';
import navHome from '/src/assets/icons/nav-home.png';
import navMypage from '/src/assets/icons/nav-mypage.png';
import logo from '/src/assets/icons/logo.png'

import * as S from './Header.style';

function Header() {
  const navigate = useNavigate();
  const goPlace = () => navigate('/place');
  const goCourse = () => navigate('/');
  const goMypage = () => navigate('/mypage');

  const location = useLocation();

  return (
    <S.Container>
      <S.SectionImg onClick={goPlace} src={navPlace} style={{ width: '40px', height: '40px', margin: '0 0 3px 10px' }} />
      <S.Logo onClick={goCourse} src={logo} />
      <S.SectionImg onClick={goMypage} src={navMypage} style={{ width: '60px', height: '60px' }} />
    </S.Container>
  );
}

export default Header;
