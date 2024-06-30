// 별점 관련 API
// 작성자: 조희정

import { privateApi } from '.';

// 장소에 별점 주기
export const giveRating = async (placeId, rating) => {
  try {
    const res = await privateApi.post(`star/place/${placeId}`, rating);
    return true;
  } catch (err) {
    console.log('giveRating catch', err);
  }
};

// 장소의 평균 별점 조회
export const getStar = async (placeId) => {
  try {
    const res = await privateApi.get(`/star/${placeId}/avg`);
    return res.data;
  } catch (err) {
    console.log('getStar catch', err);
  }
};
