import { useState } from 'react';
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

const dummy = [
  {
    id: 1,
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
  },
  {
    id: 2,
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
  },
  {
    id: 3,
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
  },
  {
    id: 4,
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
  },
  {
    id: 5,
    srcImg: FiveGuysImg,
    title: '파이브가이즈(Five Guys)',
    star: '4.3',
    category: '식당',
    info: 'B2 | 10:30 ~ 22:00',
  },
  {
    id: 6,
    srcImg: StarbucksImg,
    title: '스타벅스(Starbucks)',
    star: '4.3',
    category: '카페',
    info: 'B2 | 10:30 ~ 22:00',
  },
];

function PlacePlan() {
  const [plans, setPlans] = useState([]);
  // const pickPlan = (idx) => {
  //   plans.includes(idx) ? setPlans(plans.filter((e) => e != idx)) : setPlans([...plans, idx]);
  // };

  return (
    <>
      {dummy.map(({ id, srcImg, title, star, category, info }, idx) => (
        <S.Wrapper>
          <S.Order>
            <S.Circle>{idx + 1}</S.Circle>
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
            </LikeItem>
          </LikeContainer>
        </S.Wrapper>
      ))}
    </>
  );
}

export default PlacePlan;
