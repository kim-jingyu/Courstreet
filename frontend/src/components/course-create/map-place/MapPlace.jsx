import * as G from '../CourseCreateComponent.style';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { searchedPlacesFloorState, searchedPlacesKeywordState } from '/src/recoils/PlaceAtoms';

function MapPlace() {
  const [floor, setFloor] = useState(5);
  const [searchedFloor, setSearchedFloor] = useRecoilState(searchedPlacesFloorState);
  const [searchedKeyword, setSearchedKeyword] = useRecoilState(searchedPlacesKeywordState);

  const pickFloor = (floor) => {
    setSearchedFloor(floor)
  };

  return (
    <>
      <G.ComponentTitle>장소 선택</G.ComponentTitle>
      <h1>지도</h1>
      <h4 onClick={()=>pickFloor(0)}>전체</h4>
      <h4 onClick={()=>pickFloor(5)}>5층</h4>
      <h4 onClick={()=>pickFloor(6)}>6층</h4>
    </>
  );
}

export default MapPlace;
