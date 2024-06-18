import CourseItem from '../../course/course-item/CourseItem';
import { Tabs, Tab, CourseContainer } from '../mycourse/MyCourse.style';
import { MyCommentTab } from './MyComment.style';

function MyComment() {
  return (
    <>
      <Tabs>
        <Tab>나의 코스</Tab>
        <Tab>좋아요</Tab>
        <MyCommentTab>나의 댓글</MyCommentTab>
      </Tabs>
      <CourseContainer>
        <CourseItem />
        <CourseItem />
        <CourseItem />
      </CourseContainer>
    </>
  );
}

export default MyComment;
