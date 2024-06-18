import './MyPage.style';
import { Container, Header, Title } from './MyPage.style';
import MyComment from '/src/components/mypage/mycomment/MyComment';

function MyPage() {
  return (
    <Container>
      <Header>
        <Title>마이페이지</Title>
      </Header>
      <MyComment />
    </Container>
  );
}

export default MyPage;
