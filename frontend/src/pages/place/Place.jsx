import { useRecoilState, useRecoilValue } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import {
  selectedPlaceIdsState,
  searchedPlacesState,
  searchedMapState,
  searchedPlacesFloorState,
  searchedPlacesKeywordState,
} from '/src/recoils/PlaceAtoms';

import {FixedContainer, ScrollableContainer} from './Place.style.js';
import PlaceMap from '/src/components/place/place-map/PlaceMap';
import PlaceItem from '/src/components/place/place-item/PlaceItem';

import { Input } from 'antd';
import * as G from '/src/components/course-create/CourseCreateComponent.style';
import FiveGuysImg from '/src/assets/icons/fiveguys.png';
import StarbucksImg from '/src/assets/icons/starbucks.png';

const { Search } = Input;

function Place() {
  // 현재 컴포넌트 페이지 인덱스
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  // 검색 결과 장소들
  const searchedPlace = useRecoilValue(searchedPlacesState);
  // 현재까지 선택된 장소들, 지도 이미지, 선택된 층 변경
  const [selectedPlaceIds, setSelectedPlaceIds] = useRecoilState(selectedPlaceIdsState);
  const [searchedMap, setSearchedMap] = useRecoilState(searchedMapState);
  const [searchedFloor, setSearchedFloor] = useRecoilState(searchedPlacesFloorState);
  const pickPlace = (place_id, location, floor) => {
    setSearchedFloor(floor);
    selectedPlaceIds.includes(place_id)
      ? setSelectedPlaceIds(selectedPlaceIds.filter((e) => e != place_id))
      : setSelectedPlaceIds([...selectedPlaceIds, place_id]);
    setSearchedMap(location);
  };
  // 검색 키워드 변경
  const [_, setSearchedKeyword] = useRecoilState(searchedPlacesKeywordState);
  const onSearch = (value, e, info) => {
    setSearchedKeyword(value);
  };

  return (
    <>
    <FixedContainer>
      {/* 지도 */}
      <PlaceMap />
      {/* 검색창 */}
      <div style={{textAlign: "center"}}>
      <Search
        placeholder="장소명 입력"
        onChange={(e) => onSearch(e.target.value)}
        size="large"
        style={{
          margin: '5px auto 15px',
          width: '90%',
        }}
      />
      </div>
      </FixedContainer>
      <ScrollableContainer>
      {/* 장소들 */}
      {searchedPlace.map(({ place_id, name, phone, start_time, end_time, floor, location, category, rate, liked }) => (
        <div onClick={() => pickPlace(place_id, location, floor)} key={place_id}>
          <PlaceItem
            isSelected={selectedPlaceIds.includes(place_id)}
            srcImg={FiveGuysImg}
            name={name}
            phone={phone}
            star
            rate={rate}
            category={category}
            startTime={start_time}
            endTime={end_time}
            liked={liked}
          />
        </div>
      ))}
      </ScrollableContainer>
      <G.NextButton onClick={() => setCurrPage(2)}>일정 생성하기</G.NextButton>
    </>
  );
}

export default Place;
