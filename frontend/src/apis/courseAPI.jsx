import { publicApi, privateApi, uploadFileApi } from '.';

// 오늘의픽 조회
export const getTodayPick = async () => {
  try {
    const res = await publicApi.get(`/course/today-pick`);
    console.log('getTodayPick try', res.data);
    return res.data;
  } catch (err) {
    console.log('getTodayPick catch', err);
  }
};

// 코스 목록 전체 조회
export const getAllCourses = async () => {
  try {
    const res = await publicApi.get(`/course/all`);
    console.log('getAllCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('getAllCourses catch', err);
  }
};

// 코스 목록 검색
export const getCourses = async () => {
  try {
    const res = await publicApi.get(`/course?keyword${keyword}`);
    console.log('getCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('getCourses catch', err);
  }
};

// 추천 일정 받기
export const getRecommendedSchedule = async (data) => {
  try {
    const res = await privateApi.post(`/course/recommend`, data);
    console.log('getRecommendedSchedule try', res.data);
  } catch (err) {
    console.log('getRecommendedSchedule then', err);
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

// 코스 생성
export const createCourse = async (data) => {
  try {
    const res = await privateApi.post(`/course`, data);
    console.log('createCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('createCourse catch', err);
  }
};

// 코스 수정
export const updateCourse = async (courseId, data) => {
  try {
    const res = await privateApi.put(`/course/${courseId}`, data);
    console.log('updateCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('updateCourse catch', err);
  }
};

// 코스 삭제
export const deleteCourse = async (courseId) => {
  try {
    const res = await privateApi.delete(`/course/${courseId}`);
    console.log('deleteCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('deleteCourse catch', err);
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
    const res = await privateApi.post(`/member/cancel/like/course`, data);
    console.log('unlikeCourse try', res.data);
    return res.data;
  } catch (err) {
    console.log('unlikeCourse catch', err);
  }
};
