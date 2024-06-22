import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
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
} from './CourseItem.style';
import heartEmpty from '/src/assets/icons/heartEmpty.png';
import heartFilled from '/src/assets/icons/heartFilled.png';
import more from '/src/assets/icons/more.png';
import fiveguys from '/src/assets/icons/fiveguys.png';

function CourseItem({ course, goDetail }) {
  // 좋아요 클릭
  const [isLiked, setIsLiked] = useState(course.LIKED);
  const toggleLiked = (event) => {
    event.stopPropagation();
    setIsLiked(!isLiked);
  };

  const [open, setOpen] = useState(false);
  const [modalText, setModalText] = useState([]);
  // 모달 오픈
  const showModal = (course_id) => {
    setModalText(course_id);
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
              <UserName>{course.NICKNAME}님의 일정</UserName>
              <HeartIcon src={isLiked ? heartFilled : heartEmpty} onClick={toggleLiked} />
              {/*TODO 로그인한 아이디와 동일할 때만 MoreIcon show*/}
              <MoreIcon src={more} onClick={() => showModal(course.COURSE_ID)}></MoreIcon>
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

      <Modal open={open} onCancel={handleCancel} footer={null} centered width={150} closable={false}>
        <ModalBtn>
          <button>수정</button>
          <br />
          <button>삭제</button>
        </ModalBtn>
      </Modal>
    </>
  );
}

export default CourseItem;
