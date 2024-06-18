import { useNavigate } from 'react-router-dom';
import * as S from './NavFooter.style';
import navHome from '/src/assets/nav-home.png';
import navPlace from '/src/assets/nav-place.png';
import navMypage from '/src/assets/nav-mypage.png';

function NavFooter() {
  const navigate = useNavigate();
  const goCourse = () => navigate('/');
  const goPlace = () => navigate('/place');
  const goMypage = () => navigate('/mypage');

  return (
    <>
      <S.Container>
        <S.Section onClick={goPlace}>
          <S.SectionImage src={navPlace} alt="navPlace">
            {/* <img src={navPlace} alt="navPlace" /> */}
          </S.SectionImage>
          <S.SectionText>
            <p>지도검색</p>
          </S.SectionText>
        </S.Section>
        <S.Section onClick={goCourse}>
          <S.SectionImage src={navHome} alt="navHome">
            {/* <img src={navHome} alt="navHome" /> */}
          </S.SectionImage>
          <S.SectionText>
            <p>홈</p>
          </S.SectionText>
        </S.Section>
        <S.Section onClick={goMypage}>
          <S.SectionImage src={navMypage} alt="navMypage">
            {/* <img src={navMypage} alt="navMypage" /> */}
          </S.SectionImage>
          <S.SectionText>
            <p>마이페이지</p>
          </S.SectionText>
        </S.Section>
      </S.Container>
    </>
  );
}

export default NavFooter;
