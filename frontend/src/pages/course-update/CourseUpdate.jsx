import { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseDummyState, coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import * as S from './CourseUpdate.style';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';
import more from '/src/assets/icons/more.png';

function CourseUpdate() {
  const { courseId } = useParams();
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);
  const [course, setCourse] = useState({});

  const location = useLocation();
  const currentUrl = location.pathname;
  const updatable = currentUrl.startsWith('/courseupdate');


  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const changeTitle = (val) => {
    if (val.length < 31) setTitle(val);
  };
  const changeContent = (event) => {
    if (event.target.value.length > 500) {
      return;
    }
    event.target.style.height = 'auto';
    event.target.style.height = event.target.scrollHeight + 'px';
    setContent(event.target.value);
  };

  useEffect(() => {
    if (courseId === undefined) return;
    const res = courseDummy.find((course) => course.COURSE_ID === parseInt(courseId));
    setCourse(res);
    setTitle(res.TITLE)
    setContent(res.CONTENT)
  }, [courseId]);

  return (
    <>
      <S.Container>
        <S.CourseHeader>
          {/* <S.CourseTitle>{course.TITLE}</S.CourseTitle> */}
            <S.CourseTitleUpdate placeholder="(제목)" onChange={(e) => changeTitle(e.target.value)} value={title} />
          <S.MoreIcon src={more} onClick={() => showModal(course.COURSE_ID)}></S.MoreIcon>
        </S.CourseHeader>
          {/* <S.TitleImage>
            <img src={`/courses/${course.COURSE_ID}.jpg`} />
          </S.TitleImage> */}
          <S.TitleImageUpdate>
            <label htmlFor="uploadFile">
              <img src={`/courses/${course.COURSE_ID}.jpg`} alt="" />
            </label>
            <input
              type="file"
              id="uploadFile"
              // onChange={uploadFile}
              accept="*"
              multiple={false}
              style={{ display: 'none' }}
            />
          </S.TitleImageUpdate>

        {/* <S.CourseContent>{course.CONTENT}</S.CourseContent> */}
        <S.CourseContentUpdate placeholder="내용 입력" onChange={changeContent} value={content} />

        {/* 장소 일정 */}
        <PlacePlan />
      </S.Container>
    </>
  );
}

export default CourseUpdate;
