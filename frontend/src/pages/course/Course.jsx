import { useNavigate } from 'react-router-dom';

import TodayPick from '/src/components/course/today-pick/TodayPick';
import CourseList from '/src/components/course/course-list/CourseList';

import * as S from './Course.style';

function Course() {
  const navigate = useNavigate();
  const goCreate = () => navigate('/coursecreate');

  return (
    <>
      <TodayPick />
      <CourseList />
      <S.CreateBtn onClick={goCreate} />
    </>
  );
}

export default Course;
