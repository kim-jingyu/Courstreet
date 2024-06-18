import { AudioOutlined } from '@ant-design/icons';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import { Input, Space } from 'antd';
import * as G from '../CourseCreateComponent.style';

const { Search } = Input;
const onSearch = (value, _e, info) => console.log(info?.source, value);

function SelectPlace() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  return (
    <>
      <G.ComponentTitle>장소 선택</G.ComponentTitle>
      <h1>지도</h1>
      <Search
        placeholder="장소명을 입력하세요"
        onSearch={onSearch}
        size="large"
        style={{
          margin: '0 auto',
          width: '80%',
        }}
      />
      <br/><br/><br/><br/>
      <G.NextButton onClick={() => setCurrPage(2)}>일정 생성하기</G.NextButton>
    </>
  );
}

export default SelectPlace;
