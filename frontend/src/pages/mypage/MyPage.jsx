import './MyPage.style';
import { Container, Header, Title } from './MyPage.style';
import LikePlace from '/src/components/mypage/like-place/LikePlace';
import MyComment from '/src/components/mypage/mycomment/MyComment';

function MyPage() {
  return (
    <Container>
      <Header>
        <Title>마이페이지</Title>
      </Header>
      <LikePlace></LikePlace>
    </Container>
  );
}

export default MyPage;
