import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import SelectCategory from '/src/components/course-create/select-category/SelectCategory';
import SelectCourse from '/src/components/course-create/select-course/SelectCourse';
import Place from '../place/Place';

const Pages = [<SelectCategory />, <Place />, <SelectCourse />];

function CourseCreate() { 
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  return <>{Pages[currPage]}</>;
}

export default CourseCreate;
