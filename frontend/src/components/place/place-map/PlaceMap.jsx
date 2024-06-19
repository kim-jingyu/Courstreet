import * as G from '../../course-create/CourseCreateComponent.style';
import { useRecoilState, useRecoilValue } from 'recoil';
import { mapImageUrlState, searchedMapState, searchedPlacesFloorState } from '/src/recoils/PlaceAtoms';

function PlaceMap() {
  const [searchedFloor, setSearchedFloor] = useRecoilState(searchedPlacesFloorState);
  const [searchedMap, setSearchedMap] = useRecoilState(searchedMapState);
  const mapImageUrl = useRecoilValue(mapImageUrlState)

  const pickFloor = (floor) => {
    setSearchedFloor(floor);
    setSearchedMap(`${floor}-0`);
  };

  return (
    <>
      <G.ComponentTitle>장소 선택</G.ComponentTitle>
      <img src={`${mapImageUrl}`} alt="" />
      <h4 onClick={() => pickFloor(5)}>5층</h4>
      <h4 onClick={() => pickFloor(6)}>6층</h4>
    </>
  );
}

export default PlaceMap;
