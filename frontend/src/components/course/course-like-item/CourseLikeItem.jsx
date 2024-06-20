import {
  Container,
  DateRange,
  Dot,
  ImageBox,
  ImageGrid,
  ItemContainer,
  ItemFooter,
  ItemTitle,
  UserContainer,
  UserIcon,
  UserName,
  HeartIcon,
  HeartIconContainer,
  LeftContainer,
} from '../course-item/CourseItem.style';
import heartIcon from '/src/assets/icons/heart.png';

function CourseLikeItem() {
  return (
    <Container>
      <ItemContainer>
        <ImageGrid>
          <ImageBox />
          <ImageBox />
          <ImageBox />
          <ImageBox />
        </ImageGrid>
        <UserIcon />
        <ItemFooter>
          <UserContainer>
            <LeftContainer>
              <UserName>JADEN님의 일정</UserName>
              <Dot>·</Dot>
              <DateRange>2박 3일</DateRange>
            </LeftContainer>
            <HeartIconContainer>
              <HeartIcon src={heartIcon}></HeartIcon>
            </HeartIconContainer>
          </UserContainer>
          <ItemTitle>6일전 예약한 무작정 일본여행</ItemTitle>
        </ItemFooter>
      </ItemContainer>
    </Container>
  );
}

export default CourseLikeItem;
