import { useState, useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import PlaceItem from '../../place/place-item/PlaceItem';
import { Tabs, Tab, FilterContainer, LikeContainer, LikeTab } from '../like-post/LikePost.style';
import { PostButton, PlaceButton } from './LikePlace.style';
import CourseItem from '../../course/course-item/CourseItem';
import {
  placeDummyState,
} from '/src/recoils/PlaceAtoms';
import {
  courseDummyState,
} from '/src/recoils/CourseAtoms';



function LikePlace() {
  // 탭 활성화
  const [activeTab, setActiveTab] = useState('likedPlaces');
  const [activeLikeTab, setActiveLikeTab] = useState(true);

  // 좋아하는 장소만 보기
  const likePlace = useRecoilValue(placeDummyState);
  const likePlaces = likePlace.filter(dummy => dummy.liked)

  // 좋아하는 코스만 보기
  const likeCourse = useRecoilValue(courseDummyState);
  const likeCourses = likeCourse.filter(dummy => dummy.LIKED)

  // 내 코스만 보기
  const myCourse = useRecoilValue(courseDummyState);
  const myCourses = myCourse.filter(dummy => dummy.MEMBER_ID === 1)

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
            {likePlaces.map(({ place_id, name, phone, start_time, end_time, floor, location, category, rate, liked }) => (
              <div onClick={() => pickPlace(place_id, location, floor)} key={place_id}>
                <PlaceItem
                  srcImg={`/places/${place_id}.png`}
                  name={name}ㅖ
                  phone={phone}
                  star={rate}
                  rate={rate}
                  category={category}
                  startTime={start_time}
                  endTime={end_time}
                  liked={liked}
                  floor={floor}
                />
              </div>
            ))}
          </LikeContainer>
        )}

        {(activeTab === 'likeCourses') && (
          <LikeContainer>
            {likeCourses.map((item) => (
              <CourseItem
                course={item}
                key={item.course_id}
              />
            ))}
          </LikeContainer>
        )}

        {(activeTab === 'myCourses') && (
          <LikeContainer>
            {myCourses.map((item) => (
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
