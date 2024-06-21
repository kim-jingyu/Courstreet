import { useState, useEffect } from 'react';
import PlaceItem from '../../place/place-item/PlaceItem';
import { Tabs, Tab, FilterContainer, LikeContainer, LikeTab } from '../like-post/LikePost.style';
import { PostButton, PlaceButton } from './LikePlace.style';
import CourseItem from '../../course/course-item/CourseItem';


function LikePlace() {
  // 탭 활성화
  const [activeTab, setActiveTab] = useState('likedPlaces');
  const [curritem, setIT] = useState([]);
  // const courelist
  // const placelist



  // 탭에 따른 데이터 가져오기
  const fetchData = (tab) => {
    // 각 탭에 따른 데이터 로직을 구현
    switch (tab) {
      case 'courses':
        return [
          {
            course_id: 1,
            title: '나의 코스 1',
            description: '코스 1 설명',
            duration: '2 hours',
            difficulty: 'Intermediate',
            liked: true
          },
          // TODO 데이터 추가
          // setIT([..res, ...itemList]]
        ];
      case 'likedPlaces':
        return [
          {
            place_id: 1,
            name: '나의 좋아하는 장소 1',
            category: '식당',
            rate: '4.5',
            start_time: '10:00 AM',
            end_time: '10:00 PM',
            floor: 1,
            phone: '02-1234-5678',
            liked: true
          },
          // TODO 데이터 추가
        ];
      case 'myComments':
        return [
          {
            course_id: 1,
            title: '나의 코스 1',
            description: '코스 1 설명',
            duration: '2 hours',
            difficulty: 'Intermediate',
            liked: true
          },
          // TODO 데이터 추가
        ];
      default:
        return [];
    }
  };
  const [data, setData] = useState(fetchData(activeTab));
  useEffect(() => {
    // 탭이 변경될 때마다 해당 탭에 맞는 데이터를 다시 가져오기
    setData(fetchData(activeTab));
  }, [activeTab]);

  // 탭 클릭 시 핸들러
  const handleTabClick = (tab) => {
    setActiveTab(tab);
  };



  // 좋아요 필터
  const [showPlaceItems, setShowPlaceItems] = useState(true);
  const [showCourseItems, setShowCourseItems] = useState(true);

  const handlePostButtonClick = () => {
    setcurritem([...post, ...curr])
    setcurritem(curr.sort((a, b) => a.likedDate - b.likeDate))
    setShowPlaceItems(!showPlaceItems);
  };

  const handlePlaceButtonClick = () => {
    setShowCourseItems(!showCourseItems);
  };
  return (
    <>
      <Tabs>
        <Tab active={activeTab === 'courses'} onClick={() => handleTabClick('courses')}>나의 코스</Tab>
        <LikeTab active={activeTab === 'likedPlaces'} onClick={() => handleTabClick('likedPlaces')}>좋아요</LikeTab>
        <Tab active={activeTab === 'myComments'} onClick={() => handleTabClick('myComments')}>나의 댓글</Tab>
      </Tabs>
      <>
        <FilterContainer>
        {activeTab === 'likedPlaces' && (
            <>
              <PostButton active={showPlaceItems} onClick={handlePostButtonClick}>코스</PostButton>
              <PlaceButton active={showCourseItems} onClick={handlePlaceButtonClick}>장소</PlaceButton>
            </>
          )}
        </FilterContainer>

          <div>
          {activeTab === 'likedPlaces' && (
            <LikeContainer>
            {showPlaceItems && data.map(({ place_id, name, start_time, end_time, floor, location, rate, category, phone, liked }) => (
                <PlaceItem
                  key={place_id}
                  isSelected={false}
                  srcImg={null}
                  name={name}
                  rate={rate}
                  category={category}
                  startTime={start_time}
                  endTime={end_time}
                  floor={floor}
                  phone={phone}
                  liked={liked}
                  isModal={false}
                />
              ))}
              {showCourseItems && <CourseItem />}
            </LikeContainer>
          )}
          {(activeTab === 'courses' || activeTab === 'myComments') && (
            <LikeContainer>
              {data.map((item) => (
                <CourseItem
                  key={item.course_id}
                  title={item.title}
                  description={item.description}
                  duration={item.duration}
                  difficulty={item.difficulty}
                  liked={item.liked}
                />
              ))}
            </LikeContainer>
          )}
        </div>
      </>
    </>
  );
}

export default LikePlace;
