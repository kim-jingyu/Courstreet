import { courseDummyState } from '/src/recoils/CourseAtoms';
import { useRecoilState } from 'recoil';
import { Carousel } from 'antd';
import carousel1 from '/src/assets/icons/carousel1.png';
import carousel2 from '/src/assets/icons/carousel2.png';
import pinImg from '/src/assets/icons/todaypick-pin.png';
import { useEffect, useState } from 'react';

const contentStyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  margin: '0 auto',
  padding: 0,
  // width: '100%',
  height: '300px',
};

const imageStyle = {
  width: '100%',
  height: '100%',
  objectFit: 'cover',
};

const textstyle = {
  padding: '5px 15px 15px 15px',
  fontSize: '24px',
  fontFamily: 'Happiness-Sans-Bold',
  textAlign: 'center',
  whiteSpace: 'nowrap', // 줄 바꿈 금지
  overflow: 'hidden', // 넘치는 부분 숨김
  textOverflow: 'ellipsis', // 넘치는 텍스트에 '...' 추가
};

function TodayPick() {
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);
  const ids = [10, 4, 26, 9, 27];
  const [picks, setPicks] = useState([]);

  useEffect(() => {
    setPicks(courseDummy.filter((course) => ids.includes(course.COURSE_ID)));
  }, []);

  return (
    <>
      <div
        style={{
          margin: '-1px auto 22px 6px',
          fontSize: '25px',
          fontWeight: '900',
          display: 'flex',
          alignItems: 'center',
          fontFamily: 'Happiness-Sans-Bold',
        }}
      >
        <img src={pinImg} style={{ width: '25px', margin: '0px 4px' }}></img>
        오늘의 픽
      </div>
      <Carousel autoplay arrows infinite={true}>
        {picks.map((course) => (
          <div>
            <div style={contentStyle}>
              <img style={imageStyle} src={`/courses/${course.COURSE_ID}.jpg`} />
            </div>
            <h1 style={textstyle}>{course.TITLE}</h1>
          </div>
        ))}
      </Carousel>
    </>
  );
}

export default TodayPick;
