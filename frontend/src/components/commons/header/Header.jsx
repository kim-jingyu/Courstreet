import { useNavigate, useLocation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';

import * as S from './Header.style';
import arrowLeft from '/src/assets/icons/header-arrow-left.png';
import arrowRight from '/src/assets/icons/header-arrow-right.png';

const prevText = ['나가기', '카테고리', '장소 선택'];
const postText = ['장소 선택', '', '작성 완료'];

function Header() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  const navigate = useNavigate();
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
    <>
      <S.Container>
        {currentUrl === '/' || (
          <S.Section onClick={goPrev}>
            <img src={arrowLeft} style={{ marginRight: '6px' }}></img>
            {prevText[currPage]}
          </S.Section>
        )}
        {currentUrl === '/coursecreate' && (
          <S.Section onClick={goPost}>
            {postText[currPage]}
            <img src={arrowRight} style={{ marginLeft: '6px' }}></img>
          </S.Section>
        )}
      </S.Container>
    </>
  );
}

export default Header;
