import { useState } from 'react';
import { ComponentTitle, NextButton } from '../CourseCreateComponent.style';
import * as S from './SelectCourse.style';
import addPhoto from '/src/assets/icons/add-photo.png';
import PlacePlan from '../../place/place-plan/PlacePlan';

function SelectCourse() {
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
    <div style={{width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
      <S.CourseTitle placeholder="(제목)" onChange={(e) => changeTitle(e.target.value)} value={title} />

      <S.TitleImage>
        <img src={addPhoto} alt="" />
      </S.TitleImage>

      <S.CourseContent placeholder="내용 입력" onChange={changeContent} value={content} />

      {/* 장소 계획 */}
      <PlacePlan />
    </div>
  );
}

export default SelectCourse;
