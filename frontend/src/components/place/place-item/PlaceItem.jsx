import { useLocation } from 'react-router-dom';
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
import { StarFilled } from '@ant-design/icons';
import styled from 'styled-components';
import CheckInactive from '/src/assets/icons/check-circle-inactive.png';
import CheckActive from '/src/assets/icons/check-circle-active.png';

const CheckBox = styled.img``;

function PlaceItem({ isSelected, srcImg, name, star, category, info }) {
  // 현재 URL
  const location = useLocation();
  const currentUrl = location.pathname;
  
  return (
    <LikeContainer>
      <LikeItem style={{ paddingRight: '20px' }}>
        <ItemImage src={srcImg} alt="Five Guys" />
        <ItemDetails>
          <ItemTitle>{name}</ItemTitle>
          <ItemRating>
            <StarFilled style={{ color: '#FADB14' }} /> {star}
            <ItemTag>{category}</ItemTag>
          </ItemRating>
          <FooterDetails>{info}</FooterDetails>
        </ItemDetails>
        {currentUrl == '/coursecreate' && (
          <CheckBox style={{ width: '32px' }} src={isSelected ? CheckActive : CheckInactive} />
        )}
      </LikeItem>
    </LikeContainer>
  );
}

export default PlaceItem;
