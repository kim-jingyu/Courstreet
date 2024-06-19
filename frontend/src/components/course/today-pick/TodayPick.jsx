import { Carousel } from 'antd';
import carousel1 from '/src/assets/icons/carousel1.png';
import carousel2 from '/src/assets/icons/carousel2.png';

const contentStyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  margin: '0 auto',
  padding: 0,
  width: '100%',
  height: '240px',
  color: '#fff',
  backgroundImage: `url(${carousel1})`, // 이미지 파일 경로 설정
  backgroundSize: 'cover', // 배경 이미지 크기 설정
};

const contentStyle2 = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  margin: '0 auto',
  padding: 0,
  width: '100%',
  height: '240px',
  color: '#fff',
  backgroundImage: `url(${carousel2})`, // 이미지 파일 경로 설정
  backgroundSize: 'cover', // 배경 이미지 크기 설정
};

const textstyle = {
  whiteSpace: 'nowrap', // 줄 바꿈 금지
  overflow: 'hidden', // 넘치는 부분 숨김
  textOverflow: 'ellipsis', // 넘치는 텍스트에 '...' 추가
};

function TodayPick() {
  return (
    <>
      <h3>오늘의 픽</h3>
      <Carousel autoplay arrows infinite={true}>
        <div>
          <div style={contentStyle}></div>
          <h3 style={textstyle}>더 현대 100% 즐기기 코스 with 팝업스토어</h3>
        </div>
        <div>
          <div style={contentStyle2}></div>
          <h3 style={textstyle}>일상에 지친 5월 더현대 힐링 코스</h3>
        </div>
        <div>
          <div style={contentStyle}></div>
          <h3 style={textstyle}>더 현대 100% 즐기기 코스 with 팝업스토어</h3>
        </div>
        <div>
          <div style={contentStyle2}></div>
          <h3 style={textstyle}>일상에 지친 5월 더현대 힐링 코스</h3>
        </div>
      </Carousel>
    </>
  );
}

export default TodayPick;
