// 백화점에서 설정하는 오늘의픽
// 작성자: 남진수

import { useNavigate } from 'react-router-dom';
import { Carousel } from 'antd';
import pinImg from '/src/assets/icons/todaypick-pin.png';
import { useEffect, useState } from 'react';
import { getTodayPick } from '/src/apis/courseAPI';

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
  const navigate = useNavigate();
  const goDetail = (courseId) => navigate(`coursedetail/${courseId}`);
  const [picks, setPicks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 오늘의픽 전체 목록 조회
  useEffect(() => {
    const fetchTodayPick = async () => {
      try {
        const data = await getTodayPick();
        setPicks(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchTodayPick();
  }, []);

  // 상태별 화면 분기
  if (loading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error: {error.message}</div>;
  }
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
      <Carousel autoplay autoplaySpeed={2500} arrows infinite={true}>
        {picks.map((course, idx) => (
          <div key={idx} onClick={() => goDetail(course.courseId)}>
            <div style={contentStyle}>
              <img style={imageStyle} src={`/courses/${course.courseId}.jpg`} />
            </div>
            <h1 style={textstyle}>{course.title}</h1>
          </div>
        ))}
      </Carousel>
    </>
  );
}

export default TodayPick;
