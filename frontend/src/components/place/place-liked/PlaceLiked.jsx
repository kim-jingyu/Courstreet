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
import StarbucksImg from '/src/assets/starbucks.png';
import heartIcon from '/src/assets/heart.png';
import FiveGuysImg from '/src/assets/fiveguys.png';
import { StarFilled } from '@ant-design/icons';

function PlaceLiked() {
  return (
    <LikeContainer>
      <LikeItem>
        <ItemImage src={FiveGuysImg} alt="Five Guys" />
        <ItemDetails>
          <ItemTitle>파이브가이즈(Five Guys)</ItemTitle>
          <ItemRating>
            <StarFilled style={{ color: '#FADB14' }} /> 4.3
            <ItemTag>식당</ItemTag>
          </ItemRating>
          <FooterDetails>B2 | 10:30 ~ 22:00</FooterDetails>
        </ItemDetails>
        <HeartIcon src={heartIcon} />
      </LikeItem>
      <LikeItem>
        <ItemImage src={StarbucksImg} alt="Starbucks" />
        <ItemDetails>
          <ItemTitle>스타벅스(Starbucks)</ItemTitle>
          <ItemRating>
            <StarFilled style={{ color: '#FADB14' }} /> 4.3
            <ItemTag>카페</ItemTag>
          </ItemRating>
          <FooterDetails>B2 | 10:30 ~ 22:00</FooterDetails>
        </ItemDetails>
        <HeartIcon src={heartIcon} />
      </LikeItem>
      <LikeItem>
        <ItemImage src={FiveGuysImg} alt="Five Guys" />
        <ItemDetails>
          <ItemTitle>파이브가이즈(Five Guys)</ItemTitle>
          <ItemRating>
            <StarFilled style={{ color: '#FADB14' }} /> 4.3
            <ItemTag>식당</ItemTag>
          </ItemRating>
          <FooterDetails>B2 | 10:30 ~ 22:00</FooterDetails>
        </ItemDetails>
        <HeartIcon src={heartIcon} />
      </LikeItem>
    </LikeContainer>
  );
}

export default PlaceLiked;
