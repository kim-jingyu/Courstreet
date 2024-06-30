// 코스 생성을 위한 장소 선택 페이지
// 작성자: 김준섭

import { useRecoilState } from 'recoil';
import { useState, useEffect } from 'react';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import { selectedPlacesState } from '/src/recoils/CourseAtoms';
import { Input } from 'antd';
import PlaceItem from '../../place/place-item/PlaceItem';
import { getAllPlace } from '/src/apis/placeAPI.jsx/index.js';
import * as G from '../CourseCreateComponent.style';
import FiveGuysImg from '/src/assets/fiveguys.png';
import StarbucksImg from '/src/assets/starbucks.png';


const { Search } = Input;
const onSearch = (value, _e, info) => console.log(info?.source, value);

const dummy = [
  { id: 1, srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { id: 2, srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
  { id: 3, srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { id: 4, srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
  { id: 5, srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { id: 6, srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
];

function SelectPlace() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  const [places, setPlaces] = useRecoilState(selectedPlacesState);
  const pickPlace = (idx) => {
    places.includes(idx) ? setPlaces(places.filter((e) => e != idx)) : setPlaces([...places, idx]);
  };

  /* 장소 정보 api */
  const [place, setPlace] = useState([]);

  useEffect(() => {
    getAllPlace().then(response => {
      setPlace(response.data);
      }).catch(error => {
          console.error('Error fetching the hello message:', error);
      });
  }, []);

  return (
    <>
      <h1>지도</h1>
      <Search
        placeholder="장소명을 입력하세요"
        onSearch={onSearch}
        size="large"
        style={{
          margin: '0 auto',
          width: '80%',
        }}
      />
      <br /> <br /> <br /> <br />
      {place.map(({ placeId, srcImg, name, rate, category, info }, idx) => (
        <div onClick={() => pickPlace(idx)}>
          <PlaceItem
            key={placeId}
            isSelected={places.includes(idx)}
            srcImg={`/places/${placeId}.png`}
            name={name}
            rate={rate}
            category={category}
            info={info}
          />
        </div>
      ))}
    </>
  );
}

export default SelectPlace;
