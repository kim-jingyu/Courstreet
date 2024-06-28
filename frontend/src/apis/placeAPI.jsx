import { publicApi, privateApi } from '.';

// 장소 목록 전체 조회
export const getAllPlaces = async () => {
  try {
    const res = await publicApi.get(`/placse/all`);
    console.log('getAllPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getAllPlaces catch', err);
  }
};

// 장소 목록 검색
export const getPlaces = async (keyword) => {
  try {
    const res = await publicApi.get(`/place?keyword=${keyword}`);
    console.log('getPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getPlaces catch', err);
  }
};

// 장소 좋아요
export const likePlace = async (data) => {
  try {
    const res = await privateApi.post(`/memeber/like/place`, data);
    console.log('likePlace try', res.data);
    return res.data;
  } catch (err) {
    console.log('likePlace catch', err);
  }
};

// 장소 좋아요 취소
export const unlikePlace = async (data) => {
  try {
    const res = await privateApi.post(`/memeber/cancel/like/place`, data);
    console.log('unlikePlace try', res.data);
    return res.data;
  } catch (err) {
    console.log('unlikePlace catch', err);
  }
};

