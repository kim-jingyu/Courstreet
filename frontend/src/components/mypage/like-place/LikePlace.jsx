import { useState, useEffect } from 'react';
import { useRecoilValue, useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
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
import { getMyCourses } from '/src/apis/mypageAPI';



function LikePlace() {
  
  const navigate = useNavigate();
  const goDetail = (courseId) => navigate(`/coursedetail/${courseId}`);

  // 전체 코스
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState)

  // 전체 장소
  const [placeDummy, setPlaceDummy] = useRecoilState(placeDummyState)

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
  const [myCourse, setMyCourse] = useState([]);
  const myCourses = myCourse.filter(dummy => dummy.MEMBER_ID === 10)

  // 좋아요 누르면 장소 데이터 변경
  const handlePlaceLikeToggle = (place_id) => {
    setPlaceDummy((prevPlaces) =>
      prevPlaces.map((place) =>
        place.place_id === place_id ? { ...place, liked: !place.liked } : place
      )
    );
  };

  // 좋아요 누르면 코스 데이터 변경
  const handleCourseLikeToggle = (course_id) => {
    setCourseDummy((prevCourses) =>
      prevCourses.map((course) =>
        course.COURSE_ID === course_id ? { ...course, LIKED: !course.LIKED } : course
      )
    );
  };


  const fetchData = (tab) => {
    switch (tab) {
      case 'myCourses':
        return [
          // TODO 데이터 추가
        ];
      case 'likedPlaces':
        return [
          // TODO 데이터 추가
        ];
      case 'likeCourses':
        return [
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

  useEffect(() => {
    const fetchMyCourses = async () => {
      const data = await getMyCourses();
      if (data) {
        setMyCourse(data)
      }
    } 
    fetchMyCourses()
  }, [])

  return (
    <>
      {/* <Tabs>
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
                  onLikeToggle={() => handlePlaceLikeToggle(place_id)} 
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
                key={item.COURSE_ID}
                onLikeToggle={handleCourseLikeToggle}
                goDetail={() => goDetail(item.COURSE_ID)}

              />
            ))}
          </LikeContainer>
        )}

        {(activeTab === 'myCourses') && (
          <LikeContainer>

            {myCourses.sort((a, b) => b.COURSE_ID - a.COURSE_ID).map((item) => (
              <CourseItem
                course={item}
                key={item.COURSE_ID}
                onLikeToggle={handleCourseLikeToggle}
                goDetail={() => goDetail(item.COURSE_ID)}
              />
            ))}
          </LikeContainer>
        )}
        
      </div> */}
    </>
  );
}

export default LikePlace;
