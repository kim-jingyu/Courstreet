import './MyPage.style';
import { Container, Header, Title } from './MyPage.style';
import LikePlace from '/src/components/mypage/like-place/LikePlace';

function MyPage() {
  return (
    <Container>
      <Header>
        <Title>마이페이지</Title>
      </Header>
      <LikePlace />
    </Container>
  );
}

export default MyPage;
