import { useRecoilState } from 'recoil';
import { withCategoryState, themeCategoryState, genderCategoryState, ageCategoryState } from '/src/recoils/CourseAtoms';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import * as S from './SelectCategory.style';
import * as G from '../CourseCreateComponent.style';
import instagramLogo from '/src/assets/instagram-logo.png'

function SelectCategory() {

  const [currWith, setCurrWith] = useRecoilState(withCategoryState);
  const [currTheme, setCurrTheme] = useRecoilState(themeCategoryState);
  const [currGender, setCurrGender] = useRecoilState(genderCategoryState);
  const [currAge, setCurrAge] = useRecoilState(ageCategoryState);
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  const pickWith = (val) => (currWith === val ? setCurrWith(0) : setCurrWith(val));
  const pickTheme = (val) => (currTheme.includes(val)
    ? setCurrTheme(currTheme.filter((e) => e != val))
    : setCurrTheme([...currTheme, val])
  );
  const pickGender = (val) => (currGender === val ? setCurrGender(0) : setCurrGender(val));
  const pickAge = (val) => (currAge === val ? setCurrAge(0) : setCurrAge(val));

  return (
    <>
      <G.ComponentTitle>카테고리</G.ComponentTitle>
      <br /><br /><br /><br />
      <G.ComponentTitle>누구와 함께</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currWith === 1)} onClick={()=>pickWith(1)}><S.CategoryImage src={instagramLogo}/>혼자</S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 2)} onClick={()=>pickWith(2)}><S.CategoryImage src={instagramLogo}/>친구</S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 3)} onClick={()=>pickWith(3)}><S.CategoryImage src={instagramLogo}/>연인</S.CategorySelector>
        <S.CategorySelector isselected={+(currWith === 4)} onClick={()=>pickWith(4)}><S.CategoryImage src={instagramLogo}/>부모님</S.CategorySelector>
      </S.Section>
      <br /><br />
      <G.ComponentTitle>방문 테마</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currTheme.includes(1))} onClick={()=>pickTheme(1)}><S.CategoryImage src={instagramLogo}/>#SNS 핫플레이스</S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme.includes(2))} onClick={()=>pickTheme(2)}><S.CategoryImage src={instagramLogo}/>#쇼핑은 열정적으로</S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme.includes(3))} onClick={()=>pickTheme(3)}><S.CategoryImage src={instagramLogo}/>#맛있는 미식의 경험</S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme.includes(4))} onClick={()=>pickTheme(4)}><S.CategoryImage src={instagramLogo}/>#카페인 중독</S.CategorySelector>
        <S.CategorySelector isselected={+(currTheme.includes(5))} onClick={()=>pickTheme(5)}><S.CategoryImage src={instagramLogo}/>#쇼핑이 좋아요</S.CategorySelector>
      </S.Section>
      <br /><br />
      <G.ComponentTitle>성별</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currGender === 1)} onClick={()=>pickGender(1)}><S.CategoryImage src={instagramLogo}/>남성</S.CategorySelector>
        <S.CategorySelector isselected={+(currGender === 2)} onClick={()=>pickGender(2)}><S.CategoryImage src={instagramLogo}/>여성</S.CategorySelector>
      </S.Section>
      <br /><br />
      <G.ComponentTitle>연령</G.ComponentTitle>
      <S.Section>
        <S.CategorySelector isselected={+(currAge === 1)} onClick={()=>pickAge(1)}><S.CategoryImage src={instagramLogo}/>10대</S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 2)} onClick={()=>pickAge(2)}><S.CategoryImage src={instagramLogo}/>20대</S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 3)} onClick={()=>pickAge(3)}><S.CategoryImage src={instagramLogo}/>30대</S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 4)} onClick={()=>pickAge(4)}><S.CategoryImage src={instagramLogo}/>40대</S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 5)} onClick={()=>pickAge(5)}><S.CategoryImage src={instagramLogo}/>50대</S.CategorySelector>
        <S.CategorySelector isselected={+(currAge === 6)} onClick={()=>pickAge(6)}><S.CategoryImage src={instagramLogo}/>60대 이상</S.CategorySelector>
      </S.Section>
      <br /><br />
      <G.NextButton onClick={()=>setCurrPage(1)}>다음</G.NextButton>
    </>
  );
}

export default SelectCategory;
