import { publicApi, privateApi } from '.';

// 내가 좋아하는 장소들
export const getLikedPlaces = async (floor, keyword) => {
  try {
    const res = await privateApi.get(`/place?floor=${floor}&keyword=${keyword}`);
    console.log('getPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getPlaces catch', err);
  }
};

// 장소 좋아요
export const likePlace = async (placeId) => {
  try {
    const res = await privateApi.post(`/member/like/course/${placeId}`);
    console.log('likePlace try', res.data);
    return res.data;
  } catch (err) {
    console.log('likePlace catch', err);
  }
};

// 장소 좋아요 취소
export const unlikePlace = async (placeId) => {
  try {
    const res = await privateApi.delete(`/member/like/course/${placeId}`,);
    console.log('unlikePlace try', res.data);
    return res.data;
  } catch (err) {
    console.log('unlikePlace catch', err);
  }
};
