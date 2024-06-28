import { publicApi, privateApi } from '.';

// 코스 목록 조회, 검색
export const getCourses = async (keyword) => {
  try {
    const res = await privateApi.get(`/course?keyword=${keyword}`);
    console.log('getCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('getCourses catch', err);
  }
};

// 코스 상세페이지 조회
export const getCourse = async (courseId) => {
  try {
    const res = await privateApi.get(`/course/${courseId}`);
    console.log('getCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('getCourse catch', err);
  }
};

// 추천 일정 받기
export const getRecommendedSchedule = async (data) => {
  try {
    const res = await privateApi.post(`/course/recomend`, data);
    console.log('getRecommendedSchedule try', res.data);
  } catch (err) {
    console.log('getRecommendedSchedule then', err);
  }
};

// 코스 생성하기
export const createCourse = async (data) => {
  try {
    const res = await privateApi.post(`/course`, data);
    console.log('createPost try', res.data);
  } catch (err) {
    console.log('createPost then', err);
  }
};

// 오늘의픽 조회
export const getTodayPick = async () => {
  try {
    const res = await privateApi.get(`/course/today-pick`);
    console.log('getTodayPick try', res.data);
    return res.data;
  } catch (err) {
    console.log('getTodayPick catch', err);
  }
};

// 코스 좋아요
export const likeCourse = async (data) => {
  try {
    const res = await privateApi.post(`/member/like/course`, data);
    console.log('likeCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('likeCourse catch', err);
  }
};

// 코스 좋아요 취소
export const unlikeCourse = async (data) => {
  try {
    const res = await privateApi.delete(`/member/like/course`, data);
    console.log('unlikeCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('unlikeCourse catch', err);
  }
};
