import { atom } from 'recoil';

export const withCategoryState = atom({
  key: 'withCategoryState',
  default: 0,
});

export const themeCategoryState = atom({
  key: 'themeCategoryState',
  default: [],
});

export const genderCategoryState = atom({
  key: 'genderCategoryState',
  default: 0,
});

export const ageCategoryState = atom({
  key: 'ageCategoryState',
  default: 0,
});
