import { useRecoilState, useRecoilValue } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import { selectedPlaceIdsState, searchedPlacesState } from '/src/recoils/PlaceAtoms';
import { Input } from 'antd';
import PlaceItem from '../../place/place-item/PlaceItem';

import * as G from '../CourseCreateComponent.style';
import FiveGuysImg from '/src/assets/icons/fiveguys.png';
import StarbucksImg from '/src/assets/icons/starbucks.png';
import { searchedPlacesKeywordState } from '/src/recoils/PlaceAtoms';

const { Search } = Input;

function SelectPlace() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  const searchedPlace = useRecoilValue(searchedPlacesState);

  const [selectedPlaceIds, setSelectedPlaceIds] = useRecoilState(selectedPlaceIdsState);
  const pickPlace = (PLACE_ID) => {
    selectedPlaceIds.includes(PLACE_ID)
      ? setSelectedPlaceIds(selectedPlaceIds.filter((e) => e != PLACE_ID))
      : setSelectedPlaceIds([...selectedPlaceIds, PLACE_ID]);
  };

  const [_, setSearchedKeyword] = useRecoilState(searchedPlacesKeywordState);
  const onSearch = (value, e, info) => {
    setSearchedKeyword(value);
  };

  return (
    <>
      <Search
        placeholder="장소명 입력"
        onChange={(e) => onSearch(e.target.value)}
        // onSearch={onSearch}
        size="large"
        style={{
          margin: '0 auto',
          width: '80%',
        }}
      />
      <br /> <br /> <br /> <br />
      {searchedPlace.map(({ PLACE_ID, NAME, PHONE, START_TIME, END_TIME, LOCATION, CATEGORY }) => (
        <div onClick={() => pickPlace(PLACE_ID)}>
          <PlaceItem
            key={PLACE_ID}
            isSelected={selectedPlaceIds.includes(PLACE_ID)}
            srcImg={FiveGuysImg}
            title={NAME}
            star={'4.3'}
            category={CATEGORY}
            info={START_TIME}
          />
        </div>
      ))}
      <G.NextButton onClick={() => setCurrPage(2)}>일정 생성하기</G.NextButton>
    </>
  );
}

export default SelectPlace;
