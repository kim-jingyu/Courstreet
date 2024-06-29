import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import * as S from './CourseDetail.style';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';
import more from '/src/assets/icons/more.png';
import { getCourse } from '/src/apis/courseAPI';

function CourseDetail() {
  const { courseId } = useParams();
  const [course, setCourse] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 상세페이지 조회
  useEffect(() => {
    const fetchCourse = async () => {
      try {
        const data = await getCourse(courseId);
        setCourse(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchCourse();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error: {error.message}</div>;
  }
  return (
    <>
      <S.Container>
        <S.CourseHeader>
          <S.CourseTitle>{course.title}</S.CourseTitle>
          {localStorage.getItem('memberId') === course.memberId && (
            <S.MoreIcon src={more} onClick={() => showModal(course.courseId)}></S.MoreIcon>
          )}
        </S.CourseHeader>
        <S.TitleImage>
          <img src={`/courses/${course.courseId}.jpg`} />
        </S.TitleImage>

        <S.CourseContent>{course.content}</S.CourseContent>

        {/* 장소 일정 */}
        <PlacePlan />
      </S.Container>
    </>
  );
}

export default CourseDetail;
