import { useState } from 'react';
import { ComponentTitle, NextButton } from '../CourseCreateComponent.style';
import * as S from './SelectCourse.style';
import addPhoto from '/src/assets/add-photo.png'

function SelectCourse() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const changeTitle = (val) => {
    if (val.length < 31) setTitle(val);
    console.log(val);
  };
  const changeContent = (event) => {
    if (event.target.value.length > 500) {
      return;
    }
    event.target.style.height = 'auto';
    event.target.style.height =  event.target.scrollHeight + 'px';
    setContent(event.target.value);
  };

  return (
    <>
      <ComponentTitle>코스 생성</ComponentTitle>
      <S.CourseTitle
        placeholder="(제목)"
        onChange={(e) => changeTitle(e.target.value)}
        value={title}
        autoFocus
      />

      <S.TitleImage>
        <img src={addPhoto} alt="" />
      </S.TitleImage>

      <S.CourseContent
        className="textarea"
        placeholder="내용(500자)"
        onChange={(changeContent)}
        value={content}
      />

      <NextButton>코스 생성하기</NextButton>
    </>
  );
}

export default SelectCourse;
