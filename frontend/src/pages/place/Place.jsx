import { useRecoilState } from 'recoil';
import { selectedPlaceIdsState, searchedMapState, searchedPlacesFloorState } from '/src/recoils/PlaceAtoms';
import { FixedContainer, ScrollableContainer } from './Place.style.js';
import PlaceMap from '/src/components/place/place-map/PlaceMap';
import PlaceItem from '/src/components/place/place-item/PlaceItem';
import { Input } from 'antd';
import { useEffect, useState } from 'react';
import { getAllPlaces, searchPlaces } from '/src/apis/placeAPI.jsx';
const { Search } = Input;

function Place() {
  // 데이터
  const [places, setPlaces] = useState([]);
  const [searchedPlace, setSearchedPlace] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  // 현재까지 선택된 장소들, 지도 이미지, 선택된 층 변경
  const [selectedPlaceIds, setSelectedPlaceIds] = useRecoilState(selectedPlaceIdsState);
  const [searchedMap, setSearchedMap] = useRecoilState(searchedMapState);
  const [searchedFloor, setSearchedFloor] = useRecoilState(searchedPlacesFloorState);
  // 장소 선택
  const pickPlace = (placeId, location, floor) => {
    setSearchedFloor(floor);
    selectedPlaceIds.includes(placeId)
      ? setSelectedPlaceIds(selectedPlaceIds.filter((e) => e != placeId))
      : setSelectedPlaceIds([...selectedPlaceIds, placeId]);
    setSearchedMap(location);
  };
  // 검색 키워드 변경
  const [searchedKeyword, setSearchedKeyword] = useState('');
  const onSearch = (placeName) => {
    setSearchedKeyword(placeName);
    if (placeName.trim().length === 0) return;
    const fetchSearchPlaces = async (placeName) => {
      try {
        const data = await searchPlaces(placeName);
        setPlaces(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchSearchPlaces(placeName);
  };
  // 검색시엔 층 상관없이 검색되고, 검색어가 없이 층만 누르면 층별로 나타나게
  useEffect(() => {
    if (searchedKeyword === '') {
      setSearchedPlace(places.filter((place) => place.floor === searchedFloor));
    } else {
      setSearchedPlace(places);
    }
  }, [places, searchedFloor]);
  // 장소 전체 목록 조회
  useEffect(() => {
    const fetchAllPlaces = async () => {
      try {
        const data = await getAllPlaces();
        setPlaces(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchAllPlaces();
  }, []);

  return (
    <>
      <FixedContainer>
        <PlaceMap />

        <div style={{ textAlign: 'center' }}>
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
        {loading ? (
          <div>Loading...</div>
        ) : error ? (
          <div>Error: {error.message}</div>
        ) : (
          searchedPlace.map(
            ({ placeId, name, phone, startTime, endTime, floor, location, category, star, rate, liked }) => (
              <div onClick={() => pickPlace(placeId, location, floor)} key={placeId}>
                <PlaceItem
                  isSelected={selectedPlaceIds.includes(placeId)}
                  srcImg={`/places/${placeId}.png`}
                  name={name}
                  phone={phone}
                  star={star || 0}
                  rate={rate || 0}
                  category={category}
                  startTime={startTime}
                  endTime={startTime}
                  liked={liked}
                  floor={floor}
                  placeId={placeId}
                  onLikeToggle={() => handleLikeToggle(placeId)}
                  setPlaces={setPlaces}
                />
              </div>
            ),
          )
        )}
      </ScrollableContainer>
    </>
  );
}

export default Place;
