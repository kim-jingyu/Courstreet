import { useLocation } from 'react-router-dom';
import { Button, Modal, Rate } from 'antd';
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


const CustomRate = styled(Rate)`
  .ant-rate-star {
    margin-right: 4px; // 별 간격 조정
  }
  .ant-rate-star:first-child {
    margin-left: 8px; // 첫 번째 별 간격 조정
  }
  .ant-rate-star .anticon {
    font-size: 16px; // 별 크기 조정
  }
`;

const MyRatingContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-bottom: 16px; // 아래쪽 간격 조정
  text-align: center;

  > span {
    margin-right: 8px; // 글씨와 별 사이 간격
  }
`;


function PlaceItem({ isSelected, srcImg, name, rate, category, floor, startTime, endTime, onClick, liked, phone, isModal = false, onLikeToggle}) {
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
    onLikeToggle()
  };

  // 별점 관리 상태 추가
  const [userRate, setUserRate] = useState(rate);

  return (
    <>
    <LikeContainer>
      <LikeItem style={{ paddingRight: '20px' }} >
        <ItemImage src={srcImg} alt={name} />
        <ItemDetails>
          <ItemTitle>
            {name} {!isModal && <ItemInfo src={informationIcon} onClick={() => showModal({ name, srcImg, rate, category, floor, startTime, endTime, phone })}></ItemInfo>}
          </ItemTitle>
          <ItemRating>
          <StarFilled style={{ color: '#FADB14', marginRight: '4px' }} /> {rate}
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

    <Modal title="장소 정보" open={open} onCancel={handleCancel} footer={null} width={380}>
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
            <MyRatingContainer>
              <strong>나의 평점</strong>
              <CustomRate
                value={userRate}
                allowHalf
                onChange={(value) => setUserRate(value)}
              />
            </MyRatingContainer>
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
