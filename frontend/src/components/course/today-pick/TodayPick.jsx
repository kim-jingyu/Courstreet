import { Carousel } from 'antd';
import carousel1 from '/src/assets/icons/carousel1.png';
import carousel2 from '/src/assets/icons/carousel2.png';
import pinImg from '/src/assets/icons/todaypick-pin.png';

const contentStyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  margin: '0 auto',
  padding: 0,
  // width: '100%',
  height: '300px',
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
  height: '300px',
  backgroundImage: `url(${carousel2})`, // 이미지 파일 경로 설정
  backgroundSize: 'cover', // 배경 이미지 크기 설정
};

const textstyle = {
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  padding: '5px 60px',

  fontSize: '24px',
  fontWeight: '900',
  textAlign: 'center',
  // whiteSpace: 'nowrap', // 줄 바꿈 금지
  overflow: 'hidden', // 넘치는 부분 숨김
  textOverflow: 'ellipsis', // 넘치는 텍스트에 '...' 추가
};

function TodayPick() {
  return (
    <>
      <div style={{ margin: '-1px auto 22px 6px', fontSize: '25px', fontWeight: '900', display: 'flex', alignItems: 'center', fontFamily: 'Happiness-Sans-Bold'}}>
        <img src={pinImg} style={{width: '25px', margin: '0px 4px'}}></img>
        오늘의 픽
      </div>
      <Carousel autoplay arrows infinite={true}>
        <div>
          <div style={contentStyle}></div>
          <h1 style={textstyle}>더 현대 100% 즐기기 코스 with 팝업스토어</h1>
        </div>
        <div>
          <div style={contentStyle2}></div>
          <h1 style={textstyle}>일상에 지친 5월 더현대 힐링 코스</h1>
        </div>
        <div>
          <div style={contentStyle}></div>
          <h1 style={textstyle}>더 현대 100% 즐기기 코스 with 팝업스토어</h1>
        </div>
        <div>
          <div style={contentStyle2}></div>
          <h1 style={textstyle}>일상에 지친 5월 더현대 힐링 코스</h1>
        </div>
      </Carousel>
    </>
  );
}

export default TodayPick;
