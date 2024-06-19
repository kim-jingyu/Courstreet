import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import SelectCategory from '/src/components/course-create/select-category/SelectCategory';
import SelectPlace from '/src/components/course-create/select-place/SelectPlace';
import SelectCourse from '/src/components/course-create/select-course/SelectCourse';
import MapPlace from '/src/components/course-create/map-place/MapPlace';

const Pages = [<SelectCategory />, <><MapPlace /><SelectPlace /></>, <SelectCourse />];

function CourseCreate() { 
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  return <>{Pages[currPage]}</>;
}

export default CourseCreate;
