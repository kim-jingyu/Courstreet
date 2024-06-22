import { useState } from 'react';
import { Button, message, Steps } from 'antd';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import arrowLeft from '/src/assets/icons/header-arrow-left.png';
import arrowRight from '/src/assets/icons/header-arrow-right.png';

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
  fontFamily: 'Happiness-Sans-Bold'
}

function SelectHeader() {
  // 컴포넌트 이동
  const [current, setCurrent] = useRecoilState(courseCreateIndexState);
  const goNext = () => {
    if (current === 1) {
      if (loading) return;
      message.loading({
        content: '추천 코스를 생성중입니다..',
        duration: 2,
      });
      setLoading(true);

      setTimeout(() => {
        setLoading(false);
        setCurrent(current + 1);
      }, 2000);
    } else {
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
      duration: 2,
    });
    setLoading(true);

    setTimeout(() => {
      setLoading(false);
    }, 2000);
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
            <img src={arrowLeft} onClick={goNext} style={{height: '16px', margin: '0 5px'}} />
            이전
          </div>
        )}
        {current < steps.length - 1 && (
          <div style={buttonStyle} onClick={goNext}>
            {`다음`}
            <img src={arrowRight} onClick={goNext} style={{height: '16px', margin: '0 5px'}} />
          </div>
        )}
        {current === steps.length - 1 && (
          <div style={buttonStyle} onClick={createCourse}>
            {`생성`}
            <img src={arrowRight} onClick={goNext} style={{height: '16px', margin: '0 5px'}} />
          </div>
        )}
      </div>
    </>
  );
}
export default SelectHeader;
