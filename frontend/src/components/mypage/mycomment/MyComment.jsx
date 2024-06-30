// 내가 댓글을 작성한 코스들을 보여주는 페이지
// 작성자: 조희정

import CourseItem from '../../course/course-item/CourseItem';
import { MyCommentTab, Tabs, Tab, CourseContainer } from './MyComment.style';

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
