import { useNavigate } from 'react-router-dom';
import './CourseItem.style';
import {
  Container,
  DateRange,
  Dot,
  ImageBox,
  ImageGrid,
  ItemContainer,
  ItemFooter,
  UserIcon,
  UserName,
  ItemTitle,
  UserContainer,
} from './CourseItem.style';

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
            <UserName>{course.MEMBER_ID}님의 일정</UserName>
          </UserContainer>
          <ItemTitle>{course.TITLE}</ItemTitle>
          <div
            style={{
              display: '-webkit-box',
              WebkitLineClamp: 2,
              WebkitBoxOrient: 'vertical',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
              height: '3em', // 2줄 높이 (글꼴 크기에 따라 조정 필요)
              lineHeight: '1.5em', // 줄 높이 (글꼴 크기에 따라 조정 필요)
              fontSize: '15px'
            }}
          >
            {course.CONTENT}
          </div>
        </ItemFooter>
      </ItemContainer>
    </Container>
  );
}

export default CourseItem;
