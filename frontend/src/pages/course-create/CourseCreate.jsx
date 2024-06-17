import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { CourseCreateIndexState } from '/src/recoils/HeaderAtoms';
import SelectCategory from '/src/components/course-create/select-category/SelectCategory';
import SelectPlace from '/src/components/course-create/select-place/SelectPlace';
import SelectCourse from '/src/components/course-create/select-course/SelectCourse';

const Pages = [<SelectCategory />, <SelectPlace />, <SelectCourse />];

function CourseCreate() {
  const [currPage, setCurrPage] = useRecoilState(CourseCreateIndexState);

  return (
    <>
      {Pages[currPage]}
      <h1>Hello CourseCreate</h1>
    </>
  );
}

export default CourseCreate;
