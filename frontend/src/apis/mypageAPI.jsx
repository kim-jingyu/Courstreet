import { privateApi, publicApi } from '.';

// 내가 작성한 코스들
export const getMyCourses = async () => {
  try {
    const res = await privateApi.get(`/mypage/course`);
    console.log('getMyCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('getMyCourses catch', err);
  }
};

// 내가 좋아하는 코스들
export const getLikedCourses = async () => {
  try {
    const res = await privateApi.get(`/mypage/course/like`);
    console.log('getLikedCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('getLikedCourses catch', err);
  }
};

// 내가 좋아하는 장소들
export const getLikedPlaces = async (memberId) => {
  try {
    const res = await privateApi.get(`/mypage//place/like`);
    console.log('getLikedPlaces try', res.data);
    return res.data;
  } catch (err) {
    console.log('getLikedPlaces catch', err);
  }
};
