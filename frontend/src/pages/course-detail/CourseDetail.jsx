import { useState } from 'react';
import { ComponentTitle, NextButton } from '/src/components/course-create/CourseCreateComponent.style';
import * as G from '/src/components/course-create/select-course/SelectCourse.style';
import * as S from './CourseDetail.style';
import addPhoto from '/src/assets/icons/add-photo.png';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';

function SelectDetail() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const changeTitle = (val) => {
    if (val.length < 31) setTitle(val);
  };
  const changeContent = (event) => {
    if (event.target.value.length > 500) {
      return;
    }
    event.target.style.height = 'auto';
    event.target.style.height = event.target.scrollHeight + 'px';
    setContent(event.target.value);
  };

  return (
    <>
      <S.Container>
        <S.CourseTitle>제목</S.CourseTitle>
        <S.TitleImage>
          <img src={addPhoto} alt="" />
        </S.TitleImage>

        <S.CourseContent>asdsad</S.CourseContent>

        {/* 장소 일정 */}
        <PlacePlan/>
      </S.Container>
    </>
  );
}

export default SelectDetail;
