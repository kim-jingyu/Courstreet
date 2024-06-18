import { useRecoilState } from 'recoil';
import { courseCreateIndexState } from '/src/recoils/HeaderAtoms';
import { selectedPlacesState } from '/src/recoils/CourseAtoms';
import { Input } from 'antd';
import PlaceItem from '../../place/place-item/PlaceItem';

import * as G from '../CourseCreateComponent.style';
import FiveGuysImg from '/src/assets/fiveguys.png';
import StarbucksImg from '/src/assets/starbucks.png';

const { Search } = Input;
const onSearch = (value, _e, info) => console.log(info?.source, value);

const dummy = [
  { srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
  { srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
  { srcImg: FiveGuysImg, title: '파이브가이즈(Five Guys)', star: '4.3', category: '식당', info: 'B2 | 10:30 ~ 22:00' },
  { srcImg: StarbucksImg, title: '스타벅스(Starbucks)', star: '4.3', category: '카페', info: 'B2 | 10:30 ~ 22:00' },
];

function SelectPlace() {
  const [currPage, setCurrPage] = useRecoilState(courseCreateIndexState);
  const [places, setPlaces] = useRecoilState(selectedPlacesState);
  const pickPlace = (idx) => {
    places.includes(idx) ? setPlaces(places.filter((e) => e != idx)) : setPlaces([...places, idx]);
  };

  return (
    <>
      <G.ComponentTitle>장소 선택</G.ComponentTitle>
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
      {dummy.map(({ srcImg, title, star, category, info }, idx) => (
        <div onClick={() => pickPlace(idx)}>
          <PlaceItem
            key={idx}
            isSelected={places.includes(idx)}
            srcImg={srcImg}
            title={title}
            star={star}
            category={category}
            info={info}
          />
        </div>
      ))}
      <G.NextButton onClick={() => setCurrPage(2)}>일정 생성하기</G.NextButton>
    </>
  );
}

export default SelectPlace;
