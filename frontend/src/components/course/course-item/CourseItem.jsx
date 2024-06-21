import { useNavigate } from 'react-router-dom';
import './CourseItem.style'
import { Container, DateRange, Dot, ImageBox, ImageGrid, ItemContainer, ItemFooter, UserIcon, UserName, ItemTitle, UserContainer } from './CourseItem.style';

function CourseItem({ course, goDetail }) {

  return (
      <Container onClick={goDetail}>
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
              <UserName>{course.MEMBER_ID}</UserName>
              <Dot>·</Dot>
              <DateRange>2박 3일</DateRange>
            </UserContainer>
            <ItemTitle>{course.CONTENT}</ItemTitle>
          </ItemFooter>
        </ItemContainer>
      </Container>
    )
}

export default CourseItem;