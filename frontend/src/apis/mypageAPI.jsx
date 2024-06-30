// 마이페이지 관련 API
// 작성자: 김진규, 조희정

import { privateApi } from '.';

// 내가 작성한 코스들
export const getMyCourses = async () => {
  try {
    const res = await privateApi.get(`/mypage/course`);
    return res.data;
  } catch (err) {
    console.log('getMyCourses catch', err);
  }
};

// 내가 좋아하는 코스들
export const getLikedCourses = async () => {
  try {
    const res = await privateApi.get(`/mypage/course/like`);
    return res.data;
  } catch (err) {
    console.log('getLikedCourses catch', err);
  }
};

// 내가 좋아하는 장소들
export const getLikedPlaces = async () => {
  try {
    const res = await privateApi.get(`/mypage/place/like`);
    return res.data;
  } catch (err) {
    console.log('getLikedPlaces catch', err);
  }
};
