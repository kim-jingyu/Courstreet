import { useRecoilState, useRecoilValue } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import { selectedPlaceIdsState, searchedPlacesState, searchedMapState, searchedPlacesKeywordState } from '/src/recoils/PlaceAtoms';
import { Input } from 'antd';
import PlaceItem from '../../place/place-item/PlaceItem';

import * as G from '../CourseCreateComponent.style';
import FiveGuysImg from '/src/assets/icons/fiveguys.png';
import StarbucksImg from '/src/assets/icons/starbucks.png';

const { Search } = Input;

function SelectPlace() {
  // 현재 컴포넌트 페이지 인덱스
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  // 검색 결과 장소들
  const searchedPlace = useRecoilValue(searchedPlacesState);
  // 현재까지 선택된 장소들, 지도 이미지 변경
  const [selectedPlaceIds, setSelectedPlaceIds] = useRecoilState(selectedPlaceIdsState);
  const [searchedMap, setSearchedMap] = useRecoilState(searchedMapState);
  const pickPlace = (place_id, location) => {
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
      <Search
        placeholder="장소명 입력"
        onChange={(e) => onSearch(e.target.value)}
        size="large"
        style={{
          margin: '0 auto',
          width: '80%',
        }}
      />
      <br /> <br /> <br /> <br />
      {searchedPlace.map(({ place_id, name, PHONE, start_time, location, category }) => (
        <div onClick={() => pickPlace(place_id, location)} key={place_id}>
          <PlaceItem
            isSelected={selectedPlaceIds.includes(place_id)}
            srcImg={FiveGuysImg}
            title={name}
            star={'4.3'}
            category={category}
            info={start_time}
          />
        </div>
      ))}
      <G.NextButton onClick={() => setCurrPage(2)}>일정 생성하기</G.NextButton>
    </>
  );
}

export default SelectPlace;
