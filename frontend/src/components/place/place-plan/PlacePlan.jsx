import React from 'react';
import { useState } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import { Button, Modal } from 'antd';
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
import FiveGuysImg from '/src/assets/fiveguys.png';
import StarbucksImg from '/src/assets/starbucks.png';
import DeleteActive from '/src/assets/delete-round-active.png';
import DeleteInactive from '/src/assets/delete-round-inactive.png';
import AddPlace from '/src/assets/add-circle.png';

const dummy = [
  {
    id: '1',
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
  {
    id: '2',
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
  {
    id: '3',
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
  {
    id: '4',
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
  {
    id: '5',
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
  {
    id: '6',
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
    content: '',
  },
];

function PlacePlan() {
  const [plans, setPlans] = useState([]);
  const [items, setItems] = useState(dummy);
  const [deletedIdx, setDeletedIdx] = useState(0);
  // 메모 입력
  const changeContent = (content, id) => {
    if (content > 100) return;
    setItems((prev) => prev.map((item) => (item.id === id ? { ...item, content } : item)));
  };
  // 아이템 삭제
  const removePlace = (id) => setItems(items.filter((item) => item.id != id));
  // 아이템 재정렬
  const onDragEnd = (result) => {
    if (!result.destination) {
      return;
    }
    const reorderedItems = Array.from(items);
    const [reorderedItem] = reorderedItems.splice(result.source.index, 1);
    reorderedItems.splice(result.destination.index, 0, reorderedItem);
    setItems(reorderedItems);
  };
  // 아이템 추가
  const addPlace = () => {
    console.log('add Item!');
  }

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
        <Droppable droppableId="droppable">
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef}>
              {items.map(({ id, srcImg, title, star, category, info, content }, index) => (
                <Draggable key={id} draggableId={id} index={index}>
                  {(provided, snapshot) => (
                    <S.Wrapper ref={provided.innerRef} {...provided.draggableProps} {...provided.dragHandleProps}>
                      <S.Order>
                        <S.Circle>{index + 1}</S.Circle>
                        <S.Line style={{ border: 'none', height: '10px' }}></S.Line>
                        <S.Line></S.Line>
                      </S.Order>
                      <LikeContainer key={id} style={{ width: '80%' }}>
                        <LikeItem>
                          <ItemImage src={srcImg} alt="Five Guys" />
                          <ItemDetails>
                            <ItemTitle>{title}</ItemTitle>
                            <ItemRating>
                              <StarFilled style={{ color: '#FADB14' }} /> {star}
                              <ItemTag>{category}</ItemTag>
                            </ItemRating>
                            <FooterDetails>{info}</FooterDetails>
                          </ItemDetails>
                          <S.TrashButton src={DeleteInactive} onClick={()=> (showModal(), setDeletedIdx(id))} />
                        </LikeItem>
                        <S.Content
                          placeholder="메모 입력하기"
                          onChange={(event) => changeContent(event.target.value, id)}
                          value={content}
                        />
                      </LikeContainer>
                    </S.Wrapper>
                    // </div>
                  )}
                </Draggable>
              ))}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </DragDropContext>
      
      <br /> 
      <S.AddButton onClick={addPlace}>
        <img style={{width: '40px', height: '40px'}} src={AddPlace} />
        <div style={{fontSize: '14px'}}>장소 추가하기</div>
      </S.AddButton>
      <br /> <br /> <br />

      <Modal title="Title" open={open} onOk={handleOk} confirmLoading={confirmLoading} onCancel={handleCancel}>
        <p>{modalText}</p>
      </Modal>
    </>
  );
}

export default PlacePlan;