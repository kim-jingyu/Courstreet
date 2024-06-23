import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import {
  courseCreateContentState,
  courseCreateIndexState,
  courseCreateTitleState,
  currPlacePlanState,
} from '/src/recoils/HeaderAtoms';
import {
  ageCategoryState,
  courseDummyState,
  coursePlaceDummyState,
  genderCategoryState,
  themeCategoryState,
  withCategoryState,
} from '/src/recoils/CourseAtoms';
import arrowLeft from '/src/assets/icons/header-arrow-left.png';
import arrowRight from '/src/assets/icons/header-arrow-right.png';
import { Button, message, Steps } from 'antd';

const steps = [
  {
    title: '카테고리',
  },
  {
    title: '장소 선택',
  },
  {
    title: '코스 생성',
  },
];

const buttonStyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  fontSize: '18px',
  fontFamily: 'Happiness-Sans-Bold',
};

function SelectHeader() {
  const navigate = useNavigate();
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);
  const [coursePlaceDummy, setCoursePlaceDummy] = useRecoilState(coursePlaceDummyState);
  const [title, setTitle] = useRecoilState(courseCreateTitleState);
  const [content, setContent] = useRecoilState(courseCreateContentState);
  const [places, setPlaces] = useRecoilState(currPlacePlanState);

  // 카테고리
  const [currWith, setCurrWith] = useRecoilState(withCategoryState);
  const [currTheme, setCurrTheme] = useRecoilState(themeCategoryState);
  const [currGender, setCurrGender] = useRecoilState(genderCategoryState);
  const [currAge, setCurrAge] = useRecoilState(ageCategoryState);
  const themes = ['SNS 핫플레이스', '쇼핑은 열정적으로', '맛있는 미식의 경험', '카페인 중독', ' 도심 속 힐링'];

  // 컴포넌트 이동
  const [current, setCurrent] = useRecoilState(courseCreateIndexState);
  const goNext = () => {
    if (current === 1) {
      if (loading) return;
      message.loading({
        content: '추천 코스를 생성중입니다..',
        duration: 0.9,
      });
      setLoading(true);

      setTimeout(() => {
        setLoading(false);
        setCurrent(current + 1);
        message.success({
          content: '추천 코스를 생성되었습니다',
          duration: 1,
        });
      }, 1000);
    } else {
      // if (!currWith || !currTheme || !currGender || !currAge) {
      //   message.warning({
      //     content: '카테고리를 빠짐없이 선택해주세요',
      //     duration: 1.5,
      //   });
      //   return;
      // }
      if (!currTheme) setCurrTheme(1);
      setCurrent(current + 1);
    }
  };
  const goPrev = () => {
    setCurrent(current - 1);
  };
  const items = steps.map((item) => ({
    key: item.title,
    title: item.title,
  }));

  // 생성 후 로딩
  const [loading, setLoading] = useState(false);
  const createCourse = () => {
    if (loading) return;
    message.loading({
      content: '게시글을 생성중입니다..',
      duration: 0.9,
    });
    setLoading(true);

    const courseMaxIdx = courseDummy.reduce((maxId, course) => (course.COURSE_ID > maxId ? course.COURSE_ID : maxId), 0);
    const newCourseIdx = courseMaxIdx + 1;
    const newCousre = {
      COURSE_ID: newCourseIdx,
      MEMBER_ID: 1,
      NICKNAME: 'JADEN',
      IMAGE: '',
      TITLE: title,
      CONTENT: content,
      THEME: themes[currTheme],
      LIKED: false,
    };
    const newCoursePlace = {
      COURSE_ID: newCourseIdx,
      PLACES: places.map(({ info, memo }) => [info.place_id, memo]),
    };
    setCourseDummy((prev) => [...prev, newCousre]);
    setCoursePlaceDummy((prev) => [...prev, newCoursePlace]);

    setTimeout(() => {
      message.success({
        content: '게시글이 생성되었습니다',
        duration: 1,
      });
      navigate(`/courseDetail/${newCourseIdx}`);

      setLoading(false);
    }, 1000);
  };

  return (
    <>
      <Steps
        size="small"
        current={current}
        items={items}
        style={{ margin: '2px 0 4px 15px', display: 'flex', flexDirection: 'row', height: '16px' }}
      />
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          marginTop: 24,
        }}
      >
        {current === 0 && <div></div>}
        {current > 0 && (
          <div style={buttonStyle} onClick={goPrev}>
            <img src={arrowLeft} onClick={goNext} style={{ height: '16px', margin: '0 5px' }} />
            이전
          </div>
        )}
        {current < steps.length - 1 && (
          <div style={buttonStyle} onClick={goNext}>
            {`다음`}
            <img src={arrowRight} style={{ height: '16px', margin: '0 5px' }} />
          </div>
        )}
        {current === steps.length - 1 && (
          <div style={buttonStyle} onClick={createCourse}>
            {`생성`}
            <img src={arrowRight} style={{ height: '16px', margin: '0 5px' }} />
          </div>
        )}
      </div>
    </>
  );
}
export default SelectHeader;
