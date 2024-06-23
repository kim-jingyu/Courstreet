import { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { useRecoilState, useRecoilValue, useResetRecoilState } from 'recoil';
import { coursePlaceDummyState } from '/src/recoils/CourseAtoms';
import { placeDummyState, selectedPlaceIdsState } from '/src/recoils/PlaceAtoms';
import { currPlacePlanState, placePlanDummyState } from '/src/recoils/HeaderAtoms';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import { Modal } from 'antd';
import {
  FooterDetails,
  ItemDetails,
  ItemImage,
  ItemInfo,
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
import informationIcon from '/src/assets/icons/information.png'
import Place from '/src/pages/place/Place';
import PlaceItem from '../place-item/PlaceItem';


function PlacePlan() {
  const { courseId } = useParams();
  const location = useLocation();
  const currentUrl = location.pathname;
  const isDraggable = currentUrl.startsWith('/coursecreate') || currentUrl.startsWith('/courseupdate');
  const [selectedPlaceIds, setselectedPlaceIds] = useRecoilState(selectedPlaceIdsState);
  // 데이터 관련 변수
  const coursePlaceDummy = useRecoilValue(coursePlaceDummyState);
  const placeDummy = useRecoilValue(placeDummyState);
  const placePlanDummy = useRecoilValue(placePlanDummyState);
  const [places, setPlaces] = useRecoilState(currPlacePlanState);
  // 데이터 받기
  useEffect(() => {
    if (currentUrl.startsWith('/coursecreate')) {
      setselectedPlaceIds([]);
      setPlaces(placePlanDummy);
      return;
    }
    if (courseId === undefined) return;
    const res = coursePlaceDummy.find((coursePlace) => coursePlace.COURSE_ID === parseInt(courseId));
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
    if (content.length > 200) return; // 메모 길이 제한
    setPlaces((prevPlaces) =>
      prevPlaces.map((place) => (place.info.place_id === place_id ? { info: place.info, memo: content } : place)),
    );
  };
  // 아이템 삭제
  const removePlace = (place_id) => {
    setPlaces((prevPlaces) => prevPlaces.filter((place) => place.info.place_id !== place_id));
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

  // 장소 추가 모달
  const [addOpen, setAddOpen] = useState(false);
  const [confirmAddLoading, setConfirmAddLoading] = useState(false);
  const showAddModal = () => {
    setAddOpen(true);
  };
  const handleAddOk = () => {
    const newPlaces = placeDummy.filter((place) => selectedPlaceIds.includes(place.place_id));
    console.log(newPlaces);
    for (const place of newPlaces) {
      setPlaces((prev) => [...prev, { info: place, memo: '' }]);
    }
    setConfirmAddLoading(true);
    setTimeout(() => {
      setAddOpen(false);
      setConfirmAddLoading(false);
      setselectedPlaceIds([]);
    }, 100);
  };
  const handleAddCancel = () => {
    setAddOpen(false);
  };

  // 장소 삭제 모달
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [modalText, setModalText] = useState('정말 삭제하시겠습니까?');
  // 모달 오픈
  const showModal = () => {
    setOpen(true);
  };
  // 모달 확인 클릭
  const handleOk = () => {
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

  // 장소 상세정보 모달  
  const [openDetail, setOpenDetail] = useState(false);
  const [modalTextDetail, setModalTextDetail] = useState('');
  // 모달 오픈
  const showDetailModal = (placeDetail) => {
    console.log(placeDetail);
    setModalTextDetail(placeDetail);
    setOpenDetail(true);
  };
  // 모달 취소 클릭
  const handleCancelDetail = () => {
    setOpenDetail(false);
  };

  // // 좋아요 클릭
  // const [isLiked, setIsLiked] = useState(liked);
  // const toggleLiked = () => {
  //   setIsLiked(!isLiked);
  //   onLikeToggle()
  // };


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
                            <ItemTitle>
                              {info.name}
                              <ItemInfo
                                src={informationIcon} onClick={() => showDetailModal(info)}
                              ></ItemInfo>
                            </ItemTitle>
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
                        {isDraggable ? (
                          <S.Content
                            placeholder="메모 입력"
                            onChange={(event) => changeContent(event.target.value, info.place_id)}
                            value={memo}
                          />
                        ) : (
                          <S.ContentDiv>{memo}</S.ContentDiv>
                        )}
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
        <S.AddButton onClick={showAddModal}>
          <img style={{ width: '40px', height: '40px' }} src={AddPlace} />
          <div style={{ fontSize: '14px' }}>장소 추가하기</div>
        </S.AddButton>
      )}
      <br /> <br /> <br />
      <Modal title="장소 삭제" open={open} onOk={handleOk} confirmLoading={confirmLoading} onCancel={handleCancel}>
        <p>{modalText}</p>
      </Modal>
      <br /> <br /> <br />
      <Modal
        title="장소 추가"
        centered
        open={addOpen}
        onOk={handleAddOk}
        confirmLoading={confirmAddLoading}
        onCancel={handleAddCancel}
      >
        <Place style={{ height: '500px' }} />
      </Modal>

      <Modal title="장소 정보" open={openDetail} onCancel={handleCancelDetail} footer={null}>
        {modalTextDetail !== '' ? (
          <>
          <PlaceItem
            isSelected={false}
            srcImg={`/places/${modalTextDetail.place_id}.png`}
            name={modalTextDetail.name}
            rate={modalTextDetail.rate}
            category={modalTextDetail.category}
            startTime={modalTextDetail.start_time}
            endTime={modalTextDetail.end_time}
            floor={modalTextDetail.floor}
            liked={false}
            isModal={true}
          />
          <div>
          <strong>전화번호</strong> : {modalTextDetail.phone} <br/>
          <br/>
          <strong>영업시간</strong><br/>
          월 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          화 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          수 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          목 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          금 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          토 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          일 : {formatTime(modalTextDetail.start_time)} ~ {formatTime(modalTextDetail.end_time)}<br/>
          </div>
          </>
        ) : (
          <p>Loading...</p>
        )}
      </Modal>
    </>
  );
}

export default PlacePlan;
