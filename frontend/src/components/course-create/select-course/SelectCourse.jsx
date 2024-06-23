import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { courseCreateContentState, courseCreateTitleState } from '/src/recoils/HeaderAtoms';
import * as S from './SelectCourse.style';
import addPhoto from '/src/assets/icons/add-photo.png';
import PlacePlan from '/src/components/place/place-plan/PlacePlan';

function SelectCourse() {
  const [title, setTitle] = useRecoilState(courseCreateTitleState);
  const [content, setContent] = useRecoilState(courseCreateContentState);
  const [uploaded, setUploaded] = useState(false);

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
  const uploadFile = () => setUploaded((prev) => !prev);

  return (
    <S.Container>
      <S.CourseTitle placeholder="(제목)" onChange={(e) => changeTitle(e.target.value)} value={title} />

      <S.TitleImage>
        <label htmlFor="uploadFile">
          <img src={uploaded ? '/courses/31.jpg' : '/courses/default.jpg'} alt="" />
        </label>
        <input
          type="file"
          id="uploadFile"
          onChange={uploadFile}
          accept="*"
          multiple={false}
          style={{ display: 'none' }}
        />
      </S.TitleImage>

      <S.CourseContent placeholder="내용 입력" onChange={changeContent} value={content} />

      {/* 장소 계획 */}
      <PlacePlan />
    </S.Container>
  );
}

export default SelectCourse;
