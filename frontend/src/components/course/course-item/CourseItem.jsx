import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { courseDummyState, coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import { Modal } from 'antd';
import './CourseItem.style';
import {
  Container,
  DateRange,
  Dot,
  ImageBox,
  ImageGrid,
  ItemContainer,
  ItemFooter,
  UserIcon,
  UserName,
  ItemTitle,
  UserContainer,
  HeartIcon,
  MoreIcon,
  ModalBtn,
  ModalTitle
} from './CourseItem.style';
import heartEmpty from '/src/assets/icons/heartEmpty.png';
import heartFilled from '/src/assets/icons/heartFilled.png';
import more from '/src/assets/icons/more.png';
import fiveguys from '/src/assets/icons/fiveguys.png';
import { Section, CategorySelector } from '/src/components/course-create/select-category/SelectCategory.style';

function CourseItem({ course, goDetail, onLikeToggle }) {
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);
  // 코스 삭제
  const handleDelete = () => {
    setCourseDummy((prevCourses) =>
      prevCourses.filter((c) => c.COURSE_ID !== course.COURSE_ID)
    );
    setOpen(false);
  };

  // 좋아요 클릭
  const [isLiked, setIsLiked] = useState(course.LIKED);
  const toggleLiked = (event) => {
    event.stopPropagation();
    setIsLiked(!isLiked);
    onLikeToggle(course.COURSE_ID)
  };

  const [open, setOpen] = useState(false);
  // 모달 오픈
  const showModal = (event) => {
    event.stopPropagation();
    setOpen(true);
  };
  // 모달 취소 클릭
  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <Container onClick={goDetail}>
        <ItemContainer>
          <ImageBox>
            <img src={`/courses/${course.COURSE_ID}.jpg`}></img>
          </ImageBox>
          <UserIcon />
          <ItemFooter>
            <UserContainer>
              <UserName>{course.NICKNAME}님의 코스</UserName>
              <HeartIcon src={isLiked ? heartFilled : heartEmpty} onClick={toggleLiked} />
              {course.MEMBER_ID === 10 && (
                <MoreIcon src={more} onClick={showModal}></MoreIcon>
              )}
            </UserContainer>
            <ItemTitle>{course.TITLE}</ItemTitle>
            <div
              style={{
                display: '-webkit-box',
                paddingRight: '10px',
                WebkitLineClamp: 2,
                WebkitBoxOrient: 'vertical',
                overflow: 'hidden',
                textOverflow: 'ellipsis',
                height: '3em', // 2줄 높이 (글꼴 크기에 따라 조정 필요)
                lineHeight: '1.5em', // 줄 높이 (글꼴 크기에 따라 조정 필요)
                fontSize: '15px',
                color: 'darkgray'
              }}
            >
              {course.CONTENT}
            </div>
          </ItemFooter>
        </ItemContainer>
      </Container>

      <Modal open={open} onCancel={handleCancel} footer={null} centered width={200} closable={false} >
        <ModalTitle>{course.TITLE}</ModalTitle>
        <ModalBtn>
          <button>수정</button>
          <br />
          <button onClick={handleDelete}>삭제</button>
        </ModalBtn>
      </Modal>
    </>
  );
}

export default CourseItem;
