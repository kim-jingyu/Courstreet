import { publicApi, privateApi } from '.';

// 장소 목록 전체 조회
export const getAllPlaces = async () => {
  try {
    const res = await publicApi.get(`/places`);
    console.log('getAllPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getAllPlaces catch', err);
  }
};

// 장소 목록 검색
export const searchPlaces = async (placeName) => {
  try {
    const res = await publicApi.get(`/place?placeName=${placeName}`);
    console.log('searchPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('searchPlaces catch', err);
  }
};

// 장소 좋아요
export const likePlace = async (placeId) => {
  try {
    const res = await privateApi.post(`/like/place`, placeId);
    console.log('likePlace try', res);
    return true;
  } catch (err) {
    console.log('likePlace catch', err);
  }
};

// 장소 좋아요 취소
export const unlikePlace = async (placeId) => {
  try {
    const res = await privateApi.post(`/like/cancel/place`, placeId);
    console.log('unlikePlace try', res);
    return true;
  } catch (err) {
    console.log('unlikePlace catch', err);
  }
};
