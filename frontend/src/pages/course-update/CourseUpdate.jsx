import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseDummyState, coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import * as S from './CourseUpdate.style';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';
import more from '/src/assets/icons/more.png'

function CourseUpdate() {
  const { courseId } = useParams();
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);
  const [course, setCourse] = useState({});

  useEffect(() => {
    if (courseId === undefined) return;
    const res = courseDummy.find((course) => course.COURSE_ID === parseInt(courseId));
    setCourse(res);
  }, [courseId]);

  return (
    <>
      <S.Container>
        <S.CourseHeader>
          <S.CourseTitle>{course.TITLE}</S.CourseTitle>
          <S.MoreIcon src={more} onClick={() => showModal(course.COURSE_ID)}></S.MoreIcon>
        </S.CourseHeader>
        <S.TitleImage>
          <img src={`/courses/${course.COURSE_ID}.jpg`} />
        </S.TitleImage>

        <S.CourseContent>{course.CONTENT}</S.CourseContent>

        {/* 장소 일정 */}
        <PlacePlan />
      </S.Container>
    </>
  );
}

export default CourseUpdate;
