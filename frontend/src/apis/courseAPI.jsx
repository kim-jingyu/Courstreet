import { publicApi, privateApi } from '.';

// 오늘의픽 조회
export const getTodayPick = async () => {
  try {
    const res = await publicApi.get(`/today-pick`);
    console.log('getTodayPick try', res.data);
    return res.data;
  } catch (err) {
    console.log('getTodayPick catch', err);
  }
};

// 코스 목록 전체 조회
export const getAllCourses = async () => {
  const accessToken = localStorage.getItem('accessToken');
  // 회원
  if (accessToken) {
    try {
      // const res = await privateApi.get(`/courses/m`);
      const res = await publicApi.get(`/courses`);
      console.log('getAllCourses.m try', res.data);
      return res.data;
    } catch (err) {
      console.log('getAllCourses.m catch', err);
    }
  // 비회원
  } else {
    try {
      const res = await publicApi.get(`/courses`);
      console.log('getAllCourses try', res.data);
      return res.data;
    } catch (err) {
      console.log('getAllCourses catch', err);
    }
  }
};

// 코스 목록 검색
export const searchCourses = async (keyword) => {
  try {
    const res = await publicApi.get(`/course?keyword${keyword}`);
    console.log('searchCourses try', res.data);
    return res.data;
  } catch (err) {
    console.log('searchCourses catch', err);
  }
};

// 추천 일정 받기
export const getRecommendedSchedule = async (data) => {
  try {
    const res = await privateApi.post(`/course/recommend`, data);
    console.log('getRecommendedSchedule try', res.data);
  } catch (err) {
    console.log('getRecommendedSchedule catch', err);
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
    console.log('updateCourse try', res);
    return true;
  } catch (err) {
    console.log('updateCourse catch', err);
  }
};

// 코스 삭제
export const deleteCourse = async (courseId) => {
  try {
    const res = await privateApi.delete(`/course/${courseId}`);
    console.log('deleteCourse try', res);
    return true;
  } catch (err) {
    console.log('deleteCourse catch', err);
  }
};

// 코스 좋아요
export const likeCourse = async (courseId) => {
  try {
    const res = await privateApi.post(`/like/course/`, courseId);
    console.log('likeCourse try', res);
    return true;
  } catch (err) {
    console.log('likeCourse catch', err);
  }
};

// 코스 좋아요 취소
export const unlikeCourse = async (courseId) => {
  try {
    const res = await privateApi.post(`/like/cancel/course`, courseId);
    console.log('unlikeCourse try', res);
    return true;
  } catch (err) {
    console.log('unlikeCourse catch', err);
  }
};
