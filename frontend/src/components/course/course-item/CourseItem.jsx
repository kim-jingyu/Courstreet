// 코스 목록 아이템
// 작성자: 남진수

import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { deleteCourse, likeCourse } from '/src/apis/courseAPI';
import { Modal } from 'antd';
import './CourseItem.style';
import {
  Container,
  ImageBox,
  ItemContainer,
  ItemFooter,
  UserIcon,
  UserName,
  ItemTitle,
  UserContainer,
  HeartIcon,
  MoreIcon,
  ModalBtn,
  ModalTitle,
} from './CourseItem.style';
import heartEmpty from '/src/assets/icons/heartEmpty.png';
import heartFilled from '/src/assets/icons/heartFilled.png';
import more from '/src/assets/icons/more.png';
import profile from '/src/assets/icons/profile.png';

function CourseItem({ course, goDetail, setCourses }) {
  const navigate = useNavigate();
  // 코스 삭제
  const handleDelete = async () => {
    const res = await deleteCourse(course.courseId);
    if (res) {
      setCourses((prevCourses) => prevCourses.filter((c) => c.courseId !== course.courseId));
      setOpen(false);
    }
  };
  // 코스 수정
  const handleUpdate = () => {
    navigate(`/courseupdate/${course.courseId}`);
    setOpen(false);
  };
  // 코스 좋아요
  const toggleLiked = async (courseId, event) => {
    event.stopPropagation();
    const res = await likeCourse(courseId);
    if (res) {
      setCourses((prevCourses) =>
        prevCourses.map((course) => (course.courseId === courseId ? { ...course, liked: !course.liked } : course)),
      );
    }
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
            <img src={`/courses/${course.courseId}.jpg`}></img>
          </ImageBox>
          <UserIcon src={profile} />
          <ItemFooter>
            <UserContainer>
              <UserName>{course.nickname}님의 코스</UserName>
              <HeartIcon
                src={course.liked ? heartFilled : heartEmpty}
                onClick={(event) => toggleLiked(course.courseId, event)}
              />
              {course.memberId === 10 && <MoreIcon src={more} onClick={showModal}></MoreIcon>}
            </UserContainer>
            <ItemTitle>{course.title}</ItemTitle>
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
                color: 'darkgray',
              }}
            >
              {course.content}
            </div>
          </ItemFooter>
        </ItemContainer>
      </Container>

      <Modal open={open} onCancel={handleCancel} footer={null} centered width={200} closable={false}>
        <ModalTitle>{course.title}</ModalTitle>
        <ModalBtn>
          <button onClick={handleUpdate}>수정</button>
          <br />
          <button onClick={handleDelete}>삭제</button>
        </ModalBtn>
      </Modal>
    </>
  );
}

export default CourseItem;
