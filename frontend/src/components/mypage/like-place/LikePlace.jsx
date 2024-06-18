import PlaceLiked from '../../place/place-liked/PlaceLiked';
import { Tabs, Tab, FilterContainer, LikeContainer, LikeTab } from '../like-post/LikePost.style';
import { PostButton, PlaceButton } from './LikePlace.style';

function LikePlace() {
  return (
    <>
      <Tabs>
        <Tab>나의 코스</Tab>
        <LikeTab>좋아요</LikeTab>
        <Tab>나의 댓글</Tab>
      </Tabs>
      <>
        <FilterContainer>
          <PostButton>포스트</PostButton>
          <PlaceButton>장소</PlaceButton>
        </FilterContainer>
        <LikeContainer>
          <PlaceLiked />
          <PlaceLiked />
          <PlaceLiked />
        </LikeContainer>
      </>
    </>
  );
}

export default LikePlace;
