import './PlaceLiked.style';
import {
  FooterDetails,
  HeartIcon,
  ItemDetails,
  ItemImage,
  ItemRating,
  ItemTag,
  ItemTitle,
  LikeContainer,
  LikeItem,
} from './PlaceLiked.style';
import StarbucksImg from '/src/assets/icons/starbucks.png';
import heartIcon from '/src/assets/icons/heart.png';
import FiveGuysImg from '/src/assets/icons/fiveguys.png';
import { StarFilled } from '@ant-design/icons';
import { useState, useEffect } from 'react';
// import { getLikePlace } from '../../../apis/placeAPI.jsx/index.js';

function PlaceLiked() {
  const [place, setPlace] = useState([]);

  useEffect(() => {
    getLikePlace().then(response => {
      setPlace(response.data);
      }).catch(error => {
          console.error('Error fetching the hello message:', error);
      });
  }, []);

  return (
    <LikeContainer>
      {place.map(item => (
        <LikeItem key={item.id}>
          <ItemImage src={item.imageSrc} alt={item.name} />
          <ItemDetails>
            <ItemTitle>{item.name}</ItemTitle>
            <ItemRating>
              <StarFilled style={{ color: '#FADB14' }} /> {item.rating}
              <ItemTag>{item.category}</ItemTag>
            </ItemRating>
            <FooterDetails>{item.location}</FooterDetails>
          </ItemDetails>
          <HeartIcon src={heartIcon} />
        </LikeItem>
      ))}
    </LikeContainer>
  );
}

export default PlaceLiked;
