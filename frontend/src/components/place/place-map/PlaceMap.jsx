// 지도를 나타내는 컴포넌트
// 작성자: 조희정

import { useRecoilState, useRecoilValue } from 'recoil';
import { mapImageUrlState, searchedMapState, searchedPlacesFloorState } from '/src/recoils/PlaceAtoms';
import * as S from './PlaceMap.style';
import * as G from '../../course-create/CourseCreateComponent.style';

function PlaceMap() {
  const [searchedFloor, setSearchedFloor] = useRecoilState(searchedPlacesFloorState);
  const [searchedMap, setSearchedMap] = useRecoilState(searchedMapState);
  const mapImageUrl = useRecoilValue(mapImageUrlState);

  // 장소를 클릭하면 장소 이미지 url을 변경
  const pickFloor = (floor) => {
    setSearchedFloor(floor);
    setSearchedMap(`${floor}-0`);
  };

  return (
    <>
      <br />
      <S.Wrapper>
        <S.Floors>
          {/* <div style={{ backgroundColor: 'rgba(0, 0, 0, 0.5)', height: '100%'}}> */}

          <S.Floor focused={+(searchedFloor === 6)} onClick={() => pickFloor(6)}>
            6F
          </S.Floor>
          <S.Floor focused={+(searchedFloor === 5)} onClick={() => pickFloor(5)}>
            5F
          </S.Floor>
          <S.Floor focused={+(searchedFloor === 4)} onClick={() => pickFloor(4)}>
            4F
          </S.Floor>
          <S.Floor focused={+(searchedFloor === 3)} onClick={() => pickFloor(3)}>
            3F
          </S.Floor>
          <S.Floor focused={+(searchedFloor === 2)} onClick={() => pickFloor(2)}>
            2F
          </S.Floor>
          <S.Floor focused={+(searchedFloor === 1)} onClick={() => pickFloor(1)}>
            1F
          </S.Floor>
          {/* </div> */}
        </S.Floors>
        <img style={{ width: '90%' }} src={`${mapImageUrl}`} alt="" />
      </S.Wrapper>
      <br />
    </>
  );
}

export default PlaceMap;
