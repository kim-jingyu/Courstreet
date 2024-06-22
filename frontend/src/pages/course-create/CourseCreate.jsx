import { useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import SelectCategory from '/src/components/course-create/select-category/SelectCategory';
import SelectCourse from '/src/components/course-create/select-course/SelectCourse';
import Place from '../place/Place';
import SelectHeader from '/src/components/course-create/select-header/SelectHeader';

const Pages = [<SelectCategory />, <Place />, <SelectCourse />];

function CourseCreate() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);

  useEffect(() => {
    return () => {
      setCurrPage(0);
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
