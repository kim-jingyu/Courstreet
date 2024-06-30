// 장소 관련 API
// 작성자: 조희정

import { publicApi, privateApi } from '.';

// 장소 목록 전체 조회
export const getAllPlaces = async () => {
  try {
    const res = await publicApi.get(`/places`);
    console.log('getAllPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getAllPlaces catch', err);
    throw new Error('Failed to getAllPlaces. Please try again.')
  }
};

// 장소 목록 검색
export const searchPlaces = async (placeName) => {
  try {
    const res = await publicApi.get(`/place?placeName=${placeName}`);
    return res.data;
  } catch (err) {
    console.log('searchPlaces catch', err);
    throw new Error('Failed to searchPlaces. Please try again.')
  }
};

// 장소 좋아요
export const likePlace = async (placeId) => {
  try {
    const res = await privateApi.post(`/like/place`, placeId);
    return true;
  } catch (err) {
    console.log('likePlace catch', err);
  }
};

// 장소 좋아요 취소
export const unlikePlace = async (placeId) => {
  try {
    const res = await privateApi.post(`/like/cancel/place`, placeId);
    return true;
  } catch (err) {
    console.log('unlikePlace catch', err);
  }
};
