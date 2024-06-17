import { useNavigate, useLocation } from 'react-router-dom';
import * as S from './Header.style'
import arrowLeft from '/src/assets/header-arrow-left.png'
import arrowRight from '/src/assets/header-arrow-right.png'

function Header() {
  const navigate = useNavigate();
  const location = useLocation();
  const currentUrl = location.pathname;

  const routes = ['/', '/place', '/mypage'];

  const goPrev = () => {
    if (currentUrl == '/mypage' || currentUrl == '/place') navigate('/');
  };

  const goPost = () => {
  };

  return (
    <>
      <S.Container>
        <S.SectionPrev onClick={goPrev}>
          <img src={arrowLeft}></img>
        </S.SectionPrev>
        <S.SectionPost onClick={goPost}>
          <img src={arrowRight}></img>
        </S.SectionPost>
      </S.Container>
    </>
  );
}

export default Header;