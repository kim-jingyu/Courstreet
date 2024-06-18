import CourseLikeItem from '../../course/course-like-item/CourseLikeItem';
import { Tabs, Tab, FilterButton, FilterContainer, LikeContainer } from './LikePost.style';
import { useState } from 'react';

function LikePost() {
  const [activeTab, setActiveTab] = useState('좋아요');
  const [activeFilter, setActiveFilter] = useState('포스트');

  return (
    <>
      <Tabs>
        <Tab active={activeTab === '나의 코스'} onClick={() => setActiveTab('나의 코스')}>
          나의 코스
        </Tab>
        <Tab active={activeTab === '좋아요'} onClick={() => setActiveTab('좋아요')}>
          좋아요
        </Tab>
        <Tab active={activeTab === '나의 댓글'} onClick={() => setActiveTab('나의 댓글')}>
          나의 댓글
        </Tab>
      </Tabs>
      {activeTab === '좋아요' && (
        <>
          <FilterContainer>
            <FilterButton active={activeFilter === '포스트'} onClick={() => setActiveFilter('포스트')}>
              포스트
            </FilterButton>
            <FilterButton active={activeFilter === '장소'} onClick={() => setActiveFilter('장소')}>
              장소
            </FilterButton>
          </FilterContainer>
          <LikeContainer>
            <CourseLikeItem />
            <CourseLikeItem />
            <CourseLikeItem />
            <CourseLikeItem />
          </LikeContainer>
        </>
      )}
    </>
  );
}

export default LikePost;
