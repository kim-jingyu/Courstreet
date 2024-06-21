import { useState } from 'react';
import { Button, message, Steps } from 'antd';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';

const steps = [
  {
    title: '카테고리',
  },
  {
    title: '일정 선택',
  },
  {
    title: '코스 생성',
  },
];

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
          <Button
            style={{
              margin: '0 8px',
            }}
            onClick={goPrev}
          >
            {`<`}
          </Button>
        )}
        {current < steps.length - 1 && (
          <Button type="primary" onClick={goNext}>
            {`>`}
          </Button>
        )}
        {current === steps.length - 1 && (
          <Button type="primary" onClick={createCourse}>
            {`생성`}
          </Button>
        )}
      </div>
      <br /> <br />
    </>
  );
}
export default SelectHeader;
