import { atom, selector } from 'recoil';

// 현재까지 선택된 장소들의 id
export const selectedPlaceIdsState = atom({
  key: 'selectedPlaceIdsState',
  default: [],
});

// 검색 조건(층)
export const searchedPlacesFloorState = atom({
  key: 'searchedPlacesFloorState',
  default: 5,
});

// 검색 조건(검색어)
export const searchedPlacesKeywordState = atom({
  key: 'searchedPlacesKeywordState',
  default: '',
});

// 장소 검색 결과
export const searchedPlacesState = selector({
  key: 'searchedPlacesState',
  get: ({ get }) => {
    const dummy = get(placeDummyState);
    const floor = get(searchedPlacesFloorState);
    const keyword = get(searchedPlacesKeywordState);

    let searchedPlaces = dummy;
    if (keyword === '') {
      searchedPlaces = searchedPlaces.filter((place) => place.floor === floor);
    } else {
      searchedPlaces = searchedPlaces.filter((place) => place.name.includes(keyword));
    }
    return searchedPlaces;
  },
});

// 지도 검색 조건(층 + 장소번호)
export const searchedMapState = atom({
  key: 'searchedMapState',
  default: '5-0',
});

// 지도 이미지 경로
export const mapImageUrlState = selector({
  key: 'mapImageUrlState',
  get: ({ get }) => {
    const searchedMap = get(searchedMapState);
    return `/maps/${searchedMap}.png`;
    // return searchedMap;
  },
});