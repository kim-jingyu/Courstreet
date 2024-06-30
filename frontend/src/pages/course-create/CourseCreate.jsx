// 코스 생성 페이지
// 작성자: 김준섭

import { useEffect } from 'react';
import { useRecoilState, useResetRecoilState } from 'recoil';
import {
  courseCreateContentState,
  courseCreateIndexState,
  courseCreateTitleState,
  currPlacePlanState,
} from '/src/recoils/HeaderAtoms';
import {
  searchedMapState,
  searchedPlacesFloorState,
  searchedPlacesKeywordState,
  selectedPlaceIdsState,
} from '/src/recoils/PlaceAtoms';
import SelectCategory from '/src/components/course-create/select-category/SelectCategory';
import SelectCourse from '/src/components/course-create/select-course/SelectCourse';
import Place from '../place/Place';
import SelectHeader from '/src/components/course-create/select-header/SelectHeader';
import { ageCategoryState, genderCategoryState, themeCategoryState, withCategoryState } from '/src/recoils/CourseAtoms';

const Pages = [<SelectCategory />, <Place />, <SelectCourse />];

function CourseCreate() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  const resetCourseCreateIndex = useResetRecoilState(courseCreateIndexState);
  const resetSelectedPlaceIds = useResetRecoilState(selectedPlaceIdsState);
  const resetSearchedPlacesFloor = useResetRecoilState(searchedPlacesFloorState);
  const resetSearchedPlacesKeyword = useResetRecoilState(searchedPlacesKeywordState);
  const resetSearchedMapState = useResetRecoilState(searchedMapState);
  const resetWithCategory = useResetRecoilState(withCategoryState);
  const resetThemeCategory = useResetRecoilState(themeCategoryState);
  const resetGenderCategory = useResetRecoilState(genderCategoryState);
  const resetAgeCategory = useResetRecoilState(ageCategoryState);
  const resetCurrPlacePlan = useResetRecoilState(currPlacePlanState);
  const resetCourseCreateTitle = useResetRecoilState(courseCreateTitleState);
  const resetCreateContent = useResetRecoilState(courseCreateContentState);

  // 페이지 unmounted 시 리코일 리셋
  useEffect(() => {
    return () => {
      resetCourseCreateIndex();
      resetSelectedPlaceIds();
      resetSearchedPlacesFloor();
      resetSearchedPlacesKeyword();
      resetSearchedMapState();
      resetWithCategory();
      resetThemeCategory();
      resetGenderCategory();
      resetAgeCategory();
      resetCurrPlacePlan();
      resetCourseCreateTitle();
      resetCreateContent();
    };
  }, []);

  return (
    <>
      <SelectHeader />
      {Pages[currPage]}
    </>
  );
}

export default CourseCreate;
