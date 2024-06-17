import CourseItem from "../../course/course-item/CourseItem";
import { Tabs, Tab, FilterButton, FilterContainer, ItemDetails, ItemHeader, ItemImage, LikeContainer, LikeItem, UserName, DateRange, ItemTitle, ItemContent, HeartIcon } from "./LikePost.style";
import { useState } from "react";

function LikePost() {
    const [activeTab, setActiveTab] = useState('좋아요');
    const [activeFilter, setActiveFilter] = useState('포스트');

    return (
        <>
            <Tabs>
                <Tab active={activeTab === '나의 코스'} onClick={() => setActiveTab('나의 코스')}>나의 코스</Tab>
                <Tab active={activeTab === '좋아요'} onClick={() => setActiveTab('좋아요')}>좋아요</Tab>
                <Tab active={activeTab === '나의 댓글'} onClick={() => setActiveTab('나의 댓글')}>나의 댓글</Tab>
            </Tabs>
            {activeTab === '좋아요' && (
                <>
                <FilterContainer>
                    <FilterButton active={activeFilter === '포스트'} onClick={() => setActiveFilter('포스트')}>포스트</FilterButton>
                    <FilterButton active={activeFilter === '장소'} onClick={() => setActiveFilter('장소')}>장소</FilterButton>
                </FilterContainer>
                <LikeContainer>
                    <CourseItem />
                    <CourseItem />
                    {/* <LikeItem>
                    <ItemHeader>
                        <ItemImage />
                        <ItemDetails>
                        <UserName>JADEN님의 일정</UserName>
                        <DateRange>2박 3일</DateRange>
                        </ItemDetails>
                        <HeartIcon>❤️</HeartIcon>
                    </ItemHeader>
                    <ItemTitle>6일전 예약한 무작정 일본여행</ItemTitle>
                    <ItemContent>휴가가 어찌 될지 몰라서 출발 6일전부터 예약한 일본여행 언어무능력자, 금소심좌의 여행일정...</ItemContent>
                    </LikeItem> */}
                </LikeContainer>
                </>
            )}
        </>
    )
}

export default LikePost;