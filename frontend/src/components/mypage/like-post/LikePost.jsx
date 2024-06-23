import CourseLikeItem from '../../course/course-like-item/CourseLikeItem';
import { Tabs, Tab, FilterContainer, LikeContainer, LikeTab, PostButton, PlaceButton } from './LikePost.style';

function LikePost() {
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
          <CourseLikeItem />
          <CourseLikeItem />
          <CourseLikeItem />
          <CourseLikeItem />
        </LikeContainer>
      </>
    </>
  );
}

export default LikePost;
