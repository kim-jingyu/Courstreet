import { Tabs, Tab, CourseContainer, MyCourseTab } from './MyCourse.style';
import CourseItem from '/src/components/course/course-item/CourseItem';

function MyCourse() {
  return (
    <>
      <Tabs>
        <MyCourseTab>나의 코스</MyCourseTab>
        <Tab>좋아요</Tab>
        <Tab>나의 댓글</Tab>
      </Tabs>
      <CourseContainer>
        <CourseItem />
        <CourseItem />
        <CourseItem />
      </CourseContainer>
    </>
  );
}

export default MyCourse;
