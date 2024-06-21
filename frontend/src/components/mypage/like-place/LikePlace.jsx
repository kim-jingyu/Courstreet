import { useState, useEffect } from 'react';
import PlaceItem from '../../place/place-item/PlaceItem';
import { Tabs, Tab, FilterContainer, LikeContainer, LikeTab } from '../like-post/LikePost.style';
import { PostButton, PlaceButton } from './LikePlace.style';
import CourseItem from '../../course/course-item/CourseItem';


function LikePlace() {
  // 탭 활성화
  const [activeTab, setActiveTab] = useState('likedPlaces');
  const [activeLikeTab, setActiveLikeTab] = useState(true);

  const fetchData = (tab) => {
    switch (tab) {
      case 'myCourses':
        return [
          {
            MEMBER_ID: 10,
            course_id: 1,
            title: '나의 코스 1',
            description: '코스 1 설명',
            duration: '2 hours',
            difficulty: 'Intermediate',
            liked: true,
          },
          // TODO 데이터 추가
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
            liked: true,
          },
          // TODO 데이터 추가
        ];
      case 'likeCourses':
        return [
          {
            MEMBER_ID: 10,
            course_id: 1,
            title: '나의 코스 1',
            description: '코스 1 설명',
            duration: '2 hours',
            difficulty: 'Intermediate',
            liked: true,
          },
          // TODO 데이터 추가
        ];
      default:
        return [];
    }
  };

  const [data, setData] = useState(fetchData(activeTab));

  // 탭이 변경될 때마다 해당 탭에 맞는 데이터를 다시 가져오기
  useEffect(() => {
    setData(fetchData(activeTab));
  }, [activeTab]);

  // 탭 클릭 시 핸들러
  const handleTabClick = (tab) => {
    setActiveTab(tab);
    if (tab === 'likeCourses' || tab === 'likedPlaces') {
      setActiveLikeTab(true);
    } else {
      setActiveLikeTab(false);
    }
  };

  return (
    <>
      <Tabs>
        <Tab active={activeTab === 'myCourses'} onClick={() => handleTabClick('myCourses')}>나의 코스</Tab>
        <LikeTab active={activeLikeTab} onClick={() => handleTabClick('likeCourses')}>좋아요</LikeTab>
      </Tabs>

      {activeTab !== 'myCourses' && (
        <FilterContainer>
          <PlaceButton active={activeTab === 'likedPlaces'} onClick={() => handleTabClick('likedPlaces')}>장소</PlaceButton>
          <PostButton active={activeTab === 'likeCourses'} onClick={() => handleTabClick('likeCourses')}>코스</PostButton>
        </FilterContainer>
      )}

      <div>
        {activeTab === 'likedPlaces' && (
          <LikeContainer>
            {data.map(({ place_id, name, start_time, end_time, floor, location, rate, category, phone, liked }) => (
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
          </LikeContainer>
        )}

        { (activeTab === 'likeCourses' || activeTab === 'myCourses') && (
          <LikeContainer>
            {data.map((item) => (
              <CourseItem
                course={item}
                key={item.course_id}
              />
            ))}
          </LikeContainer>
        )}
      </div>
    </>
  );
}

export default LikePlace;
