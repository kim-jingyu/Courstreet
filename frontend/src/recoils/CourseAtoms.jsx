import { atom } from 'recoil';

// 누구랑 함께 키워드
export const withCategoryState = atom({
  key: 'withCategoryState',
  default: 0,
});

// 테마 키워드
export const themeCategoryState = atom({
  key: 'themeCategoryState',
  default: [],
});

// 성별 키워드
export const genderCategoryState = atom({
  key: 'genderCategoryState',
  default: 0,
});

// 연령 키워드
export const ageCategoryState = atom({
  key: 'ageCategoryState',
  default: 0,
});

