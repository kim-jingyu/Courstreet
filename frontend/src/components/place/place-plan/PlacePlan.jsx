import { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import { placeDummyState } from '/src/recoils/PlaceAtoms';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import { Modal } from 'antd';
import {
  FooterDetails,
  ItemDetails,
  ItemImage,
  ItemRating,
  ItemTag,
  ItemTitle,
  LikeContainer,
  LikeItem,
} from '../place-liked/PlaceLiked.style';
import * as S from './PlacePlan.style';
import { StarFilled } from '@ant-design/icons';
import DeleteInactive from '/src/assets/icons/delete-round-inactive.png';
import AddPlace from '/src/assets/icons/add-circle.png';

const createDummy = [
  {
    info: {
      place_id: 3,
      name: '랑만',
      phone: '02-3277-0656',
      start_time: '2024-06-01 13:00',
      end_time: '2024-06-01 20:00',
      floor: 6,
      location: '6-3',
      category: '식당',
      rate: 3.2,
      liked: false,
    },
    memo: '',
  },
  {
    info: {
      place_id: 24,
      name: '슈퍼말차',
      phone: '02-3277-8517',
      start_time: '2024-06-01 13:00',
      end_time: '2024-06-01 20:00',
      floor: 6,
      location: '6-13',
      category: '카페',
      rate: 3.3,
      liked: true,
    },
    memo: '',
  },
  {
    info: {
      place_id: 53,
      name: '디즈니스토어',
      phone: '02-3277-8546',
      start_time: '2024-06-01 13:00',
      end_time: '2024-06-01 20:00',
      floor: 5,
      location: '5-29',
      category: '엔터테인먼트',
      rate: 1.1,
      liked: true,
    },
    memo: '',
  },
  {
    info: {
      place_id: 49,
      name: '록시땅',
      phone: '02-3277-8542',
      start_time: '2024-06-01 13:00',
      end_time: '2024-06-01 20:00',
      floor: 5,
      location: '5-27',
      category: '쇼핑',
      rate: 2.3,
      liked: false,
    },
    memo: '',
  },
];

function PlacePlan() {
  const { courseId } = useParams();
  const location = useLocation();
  const currentUrl = location.pathname;
  const isDraggable = currentUrl.startsWith('/coursecreate');
  // 데이터 관련 변수
  const [coursePlaceDummy, setCoursePlaceDummy] = useRecoilState(coursePlaceDummyState);
  const [coursePlace, setCoursePlace] = useState({});
  const [placeDummy, setPlaceDummy] = useRecoilState(placeDummyState);
  const [places, setPlaces] = useState([]);
  // 데이터 받기
  useEffect(() => {
    if (isDraggable) {
      setPlaces(createDummy);
      return;
    }
    if (courseId === undefined) return;
    const res = coursePlaceDummy.find((coursePlace) => coursePlace.COURSE_ID === parseInt(courseId));
    setCoursePlace(res);
    setPlaces([]);
    for (const [PLACE_ID, MEMO] of res.PLACES) {
      const place = placeDummy.find((place) => place.place_id === PLACE_ID);
      setPlaces((prev) => [...prev, { info: place, memo: MEMO }]);
    }
  }, [courseId, isDraggable]);
  // 시간 포매팅
  const formatTime = (dateString) => {
    const date = new Date(dateString);
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${hours}:${minutes}`;
  };

  // 드래그 관련 변수
  const [deletedIdx, setDeletedIdx] = useState(0);
  // 메모 입력
  const changeContent = (content, place_id) => {
    console.log(content, place_id);
    if (content.length > 100) return; // 메모 길이 제한
    setPlaces((prevItems) =>
      prevItems.map((item) => (item.info.place_id === place_id ? { info: item.info, memo: content } : item)),
    );
  };
  // 아이템 삭제
  const removePlace = (place_id) => {
    setPlaces((prevItems) => prevItems.filter((item) => item.info.place_id !== place_id));
  };
  // 아이템 재정렬
  const onDragEnd = (result) => {
    if (!result.destination) {
      return;
    }
    const reorderedItems = Array.from(places);
    const [reorderedItem] = reorderedItems.splice(result.source.index, 1);
    reorderedItems.splice(result.destination.index, 0, reorderedItem);
    setPlaces(reorderedItems);
  };
  const addPlace = () => {
    console.log('add Item!');
  };

  // 모달 관련 변수
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [modalText, setModalText] = useState('정말 삭제하시겠습니까?');
  // 모달 오픈
  const showModal = () => {
    setOpen(true);
  };
  // 모달 확인 클릭
  const handleOk = (id) => {
    setModalText('장소를 삭제중입니다..');
    setConfirmLoading(true);
    // 희박하지만 setDeletedIdx가 느리다면 오류 가능성
    setTimeout(() => {
      setOpen(false);
      setConfirmLoading(false);
      setModalText('정말 삭제하시겠습니까?');
      removePlace(deletedIdx);
    }, 1000);
  };
  // 모달 취소 클릭
  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <DragDropContext onDragEnd={onDragEnd}>
        <Droppable droppableId={'droppable'}>
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef}>
              {places.map(({ info, memo }, index) => (
                <Draggable
                  key={info.place_id}
                  draggableId={`${info.place_id}`}
                  index={index}
                  isDragDisabled={!isDraggable}
                >
                  {(provided, snapshot) => (
                    <S.Wrapper ref={provided.innerRef} {...provided.draggableProps} {...provided.dragHandleProps}>
                      <S.Order>
                        <S.Circle>{index + 1}</S.Circle>
                        <S.Line></S.Line>
                      </S.Order>
                      <LikeContainer key={info.place_id} style={{ width: '80%' }}>
                        <LikeItem>
                          <ItemImage src={`/places/${info.place_id}.png`} />
                          <ItemDetails>
                            <ItemTitle>{info.name}</ItemTitle>
                            <ItemRating>
                              <StarFilled style={{ color: '#FADB14' }} /> {info.rate}
                              <ItemTag>{info.category}</ItemTag>
                            </ItemRating>
                            <FooterDetails>
                              {info.floor}층 | {formatTime(info.start_time)} - {formatTime(info.end_time)}
                            </FooterDetails>
                          </ItemDetails>
                          {isDraggable && (
                            <S.TrashButton
                              src={DeleteInactive}
                              onClick={() => (showModal(), setDeletedIdx(info.place_id))}
                            />
                          )}
                        </LikeItem>
                        {isDraggable && (
                          <S.Content
                            placeholder="메모 입력"
                            onChange={(event) => changeContent(event.target.value, info.place_id)}
                            // onChange={(event) => {console.log(event.target.value)}}
                            value={memo}
                          />
                        )}
                        {!isDraggable && <S.ContentDiv>{memo}</S.ContentDiv>}
                      </LikeContainer>
                    </S.Wrapper>
                  )}
                </Draggable>
              ))}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </DragDropContext>
      <br />
      {isDraggable && (
        <S.AddButton onClick={addPlace}>
          <img style={{ width: '40px', height: '40px' }} src={AddPlace} />
          <div style={{ fontSize: '14px' }}>장소 추가하기</div>
        </S.AddButton>
      )}
      <br /> <br /> <br />
      <Modal title="삭제" open={open} onOk={handleOk} confirmLoading={confirmLoading} onCancel={handleCancel}>
        <p>{modalText}</p>
      </Modal>
    </>
  );
}

export default PlacePlan;
