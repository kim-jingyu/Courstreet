import './MyPage.style';
import { Container, Header, Title } from './MyPage.style';
import LikePost from '/src/components/mypage/like-post/LikePost';
// import MyCourse from '/src/components/mypage/mycourse/MyCourse';


function MyPage() {
  return (
    <Container>
      <Header>
        <Title>마이페이지</Title>
      </Header>
      <LikePost />
    </Container>
  );
}

export default MyPage;
