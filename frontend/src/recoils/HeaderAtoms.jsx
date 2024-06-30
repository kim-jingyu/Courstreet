// 헤더 관련 전역 변수
// 작성자: 김준섭

import { atom } from 'recoil';

// 코스 생성 페이지에서의 컴포넌트 인덱스
export const courseCreateIndexState = atom({
  key: 'courseCreateIndexState',
  default: 0,
});

// 코스 생성 페이지에서의 제목
export const courseCreateTitleState = atom({
  key: 'courseCreateTitleState',
  default: '',
});

// 코스 생성 페이지에서의 내용
export const courseCreateContentState = atom({
  key: 'courseCreateContentState',
  default: '',
});

// 현재 화면에 보여주는 장소들 일정
export const currPlacePlanState = atom({
  key: 'currPlacePlanState',
  default: [],
});

// 코스 생성 페이지의 추천 일정 더미데이터
export const placePlanDummyState = atom({
  key: 'placePlanDummyState',
  default: [
    {
      info: {
        place_id: 53,
        name: '디즈니스토어',
        phone: '02-3277-8546',
        start_time: '2024-06-01 13:00',
        end_time: '2024-06-01 20:00',
        floor: 5,
        location: '5-29',
        category: '엔터테인먼트',
        rate: 2.3,
        liked: true,
      },
      memo: '',
    },
    {
      info: {
        place_id: 3,
        name: '랑만',
        phone: '02-3277-0656',
        start_time: '2024-06-01 13:00',
        end_time: '2024-06-01 20:00',
        floor: 6,
        location: '6-3',
        category: '식당',
        rate: 3.2,
        liked: false,
      },
      memo: '',
    },
    {
      info: {
        place_id: 24,
        name: '슈퍼말차',
        phone: '02-3277-8517',
        start_time: '2024-06-01 13:00',
        end_time: '2024-06-01 20:00',
        floor: 6,
        location: '6-13',
        category: '카페',
        rate: 3.3,
        liked: true,
      },
      memo: '',
    },
    {
      info: {
        place_id: 27,
        name: '호라이즌16',
        phone: '02-3277-8520',
        start_time: '2024-06-01 13:00',
        end_time: '2024-06-01 20:00',
        floor: 5,
        location: '5-12',
        category: '베이커리',
        rate: 2.4,
        liked: true,
      },
      memo: '',
    },
    {
      info: {
        place_id: 49,
        name: '록시땅',
        phone: '02-3277-8542',
        start_time: '2024-06-01 13:00',
        end_time: '2024-06-01 20:00',
        floor: 5,
        location: '5-27',
        category: '쇼핑',
        rate: 4.2,
        liked: false,
      },
      memo: '',
    },
  ],
});
