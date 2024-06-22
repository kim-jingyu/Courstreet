import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseDummyState, coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import * as G from '/src/components/course-create/select-course/SelectCourse.style';
import * as S from './CourseDetail.style';
import addPhoto from '/src/assets/icons/add-photo.png';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';

function SelectDetail() {
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
        <S.CourseTitle>{course.TITLE}</S.CourseTitle>
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

export default SelectDetail;
