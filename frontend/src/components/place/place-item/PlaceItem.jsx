import { useLocation } from 'react-router-dom';
import { Button, Modal } from 'antd';
import { useState } from 'react';
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
  HeartIcon
} from '../place-liked/PlaceLiked.style';
import { StarFilled } from '@ant-design/icons';
import styled from 'styled-components';
import CheckInactive from '/src/assets/icons/check-circle-inactive.png';
import CheckActive from '/src/assets/icons/check-circle-active.png';
import informationIcon from '/src/assets/icons/information.png'
import heartEmpty from '/src/assets/icons/heartEmpty.png';
import heartFilled from '/src/assets/icons/heartFilled.png';


const CheckBox = styled.img``;

function formatTime(dateString) {
  const date = new Date(dateString);
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  return `${hours}:${minutes}`;
}



function PlaceItem({ isSelected, srcImg, name, rate, category, floor, startTime, endTime, onClick, liked, phone, isModal = false }) {
  // 현재 URL
  const location = useLocation();
  const currentUrl = location.pathname;


  const [open, setOpen] = useState(false);
  const [modalText, setModalText] = useState([]);
  // 모달 오픈
  const showModal = (placdDetail) => {
    setModalText(placdDetail);
    setOpen(true);
  };
  // 모달 취소 클릭
  const handleCancel = () => {
    setOpen(false);
  };

  // 좋아요 클릭
  const [isLiked, setIsLiked] = useState(liked);
  const toggleLiked = () => {
    setIsLiked(!isLiked);
  };

  return (
    <>
    <LikeContainer>
      <LikeItem style={{ paddingRight: '20px' }} >
        <ItemImage src={srcImg} alt="Five Guys" />
        <ItemDetails>
          <ItemTitle>
            {name} {!isModal && <ItemInfo src={informationIcon} onClick={() => showModal({ name, srcImg, rate, category, floor, startTime, endTime, phone })}></ItemInfo>}
          </ItemTitle>
          <ItemRating>
            <StarFilled style={{ color: '#FADB14' }} /> {rate}
            <ItemTag>{category}</ItemTag>
          </ItemRating>
          <FooterDetails>{floor}층 | {formatTime(startTime)} - {formatTime(endTime)}</FooterDetails>
        </ItemDetails>
        {currentUrl !== '/coursecreate' && !isModal && (
            <HeartIcon src={isLiked ? heartFilled : heartEmpty} onClick={toggleLiked} />
          )}
          {currentUrl === '/coursecreate' && (
            isLiked ? <HeartIcon src={heartFilled} /> : null
          )}
          {currentUrl === '/coursecreate' && (
            <CheckBox style={{ width: '32px' }} src={isSelected ? CheckActive : CheckInactive} />
          )}
          {isModal && liked && <HeartIcon src={heartFilled} />}
      </LikeItem>
    </LikeContainer>

    <Modal title="장소 정보" open={open} onCancel={handleCancel} footer={null}>
        {modalText ? (
          <>
          <PlaceItem
            isSelected={false}
            srcImg={modalText.srcImg}
            name={modalText.name}
            rate={modalText.rate}
            category={modalText.category}
            startTime={modalText.startTime}
            endTime={modalText.endTime}
            floor={modalText.floor}
            liked={false}
            isModal={true}
          />
          <div>
          <strong>전화번호</strong> : {modalText.phone} <br/>
          <br/>
          <strong>영업시간</strong><br/>
          월 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          화 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          수 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          목 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          금 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          토 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          일 : {formatTime(modalText.startTime)} ~ {formatTime(modalText.endTime)}<br/>
          </div>
          </>
        ) : (
          <p>Loading...</p>
        )}
      </Modal>
    </>
  );
}

export default PlaceItem;
