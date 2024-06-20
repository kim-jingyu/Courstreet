import { atom } from 'recoil';

// 코스 생성 페이지에서의 컴포넌트 인덱스
export const courseCreateIndexState = atom({
  key: 'courseCreateIndexState',
  default: 0,
});
