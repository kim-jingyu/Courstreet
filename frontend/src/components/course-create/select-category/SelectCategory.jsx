import { useRecoilState } from 'recoil';
import { withCategoryState, themeCategoryState, genderCategoryState, ageCategoryState } from '/src/recoils/CourseAtoms';

import * as S from './SelectCategory.style';
import * as G from '../CourseCreateComponent.style';
import { Calendar, TimePicker, theme } from 'antd';

function SelectCategory() {
  // antd 토큰
  const { token } = theme.useToken();
  // 달력과 시간
  const onSelect = (val) => {
    // setValue(newValue);
    // setSelectedValue(newValue);
    console.log(val);
  };
  const onTimeChange = (time, timeString) => {
    console.log(time, timeString);
  };
  const CalenderStyle = {
    margin: '10px auto',
    width: 360,
    border: `2px solid ${token.colorBorderSecondary}`,
    borderRadius: token.borderRadiusLG,
  };

  // 카테고리
  const [currWith, setCurrWith] = useRecoilState(withCategoryState);
  const [currTheme, setCurrTheme] = useRecoilState(themeCategoryState);
  const [currGender, setCurrGender] = useRecoilState(genderCategoryState);
  const [currAge, setCurrAge] = useRecoilState(ageCategoryState);

  const pickWith = (val) => (currWith === val ? setCurrWith(0) : setCurrWith(val));
  const pickTheme = (val) => currTheme === val ? setCurrTheme(0) : setCurrTheme(val);
  const pickGender = (val) => (currGender === val ? setCurrGender(0) : setCurrGender(val));
  const pickAge = (val) => (currAge === val ? setCurrAge(0) : setCurrAge(val));

  return (
    <>
      <G.ComponentTitle>방문일</G.ComponentTitle>
      <div style={CalenderStyle}>
        <Calendar fullscreen={false} onSelect={onSelect} />
      </div>
      <TimePicker.RangePicker placeholder={['방문예정시간', '방문종료시간']} use12Hours format="h a" onChange={onTimeChange} size="large" />
      <br /> <br />
      <G.ComponentTitle>누구와 함께</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currWith === 1)} onClick={() => pickWith(1)}>
          혼자
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 2)} onClick={() => pickWith(2)}>
          친구
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 3)} onClick={() => pickWith(3)}>
          연인
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 4)} onClick={() => pickWith(4)}>
          부모님
        </S.CategorySelector>
      </S.Section>
      <br /> <br />
      <G.ComponentTitle>방문 테마</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currTheme === 1)} onClick={() => pickTheme(1)}>
          #SNS 핫플레이스
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme === 2)} onClick={() => pickTheme(2)}>
          #쇼핑은 열정적으로
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme === 3)} onClick={() => pickTheme(3)}>
          #맛있는 미식의 경험
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme === 4)} onClick={() => pickTheme(4)}>
          #카페인 중독
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme === 5)} onClick={() => pickTheme(5)}>
          #도심 속 힐링
        </S.CategorySelector>
      </S.Section>
      <br /> <br />
      <G.ComponentTitle>성별</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currGender === 1)} onClick={() => pickGender(1)}>
          남성
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currGender === 2)} onClick={() => pickGender(2)}>
          여성
        </S.CategorySelector>
      </S.Section>
      <br /> <br />
      <G.ComponentTitle>연령</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currAge === 1)} onClick={() => pickAge(1)}>
          10대
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 2)} onClick={() => pickAge(2)}>
          20대
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 3)} onClick={() => pickAge(3)}>
          30대
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 4)} onClick={() => pickAge(4)}>
          40대
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 5)} onClick={() => pickAge(5)}>
          50대
        </S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 6)} onClick={() => pickAge(6)}>
          60대 이상
        </S.CategorySelector>
      </S.Section>
      <br /> <br />
    </>
  );
}

export default SelectCategory;
