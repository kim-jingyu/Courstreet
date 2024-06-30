// 코스 관련 API
// 작성자: 김준섭, 남진수

import { publicApi, privateApi } from '.';

// 오늘의픽 조회
export const getTodayPick = async () => {
  try {
    const res = await publicApi.get(`/today-pick`);
    return res.data;
  } catch (err) {
    console.log('getTodayPick catch', err);
    throw new Error('Failed to getTodayPick. Please try again.');
  }
};

// 코스 목록 전체 조회
export const getAllCourses = async () => {
  const memberId = localStorage.getItem('memberId');
  // 회원
  if (memberId) {
    try {
      // const res = await privateApi.get(`/courses/m`);
      const res = await publicApi.get(`/courses`);
      return res.data;
    } catch (err) {
      console.log('getAllCourses.m catch', err);
      throw new Error('Failed to fetch courses. Please try again.');
    }
    // 비회원
  } else {
    try {
      const res = await publicApi.get(`/courses`);
      return res.data;
    } catch (err) {
      console.log('getAllCourses catch', err);
      throw new Error('Failed to fetch courses. Please try again.');
    }
  }
};

// 코스 목록 검색
export const searchCourses = async (keyword) => {
  try {
    const res = await publicApi.get(`/course?keyword${keyword}`);
    return res.data;
  } catch (err) {
    console.log('searchCourses catch', err);
    throw new Error('Failed to fetch searchCourses. Please try again.');
  }
};

// 추천 일정 받기
export const getRecommendedSchedule = async (data) => {
  try {
    const res = await privateApi.post(`/course/recommend`, data);
  } catch (err) {
    console.log('getRecommendedSchedule catch', err);
  }
};

// 코스 상세페이지 조회
export const getCourse = async (courseId) => {
  try {
    const res = await privateApi.get(`/course/${courseId}`);
    return res.data;
  } catch (err) {
    console.log('getCourse catch', err);
  }
};

// 코스를 위한 장소 일정 추천받기
export const recommendPlan = async (data) => {
  try {
    const res = await privateApi.post(`/course/recommend`, data);
    return res.data;
  } catch (err) {
    console.log('recommendPlan catch', err);
  }
};

// 코스 생성
export const createCourse = async (data) => {
  try {
    const res = await privateApi.post(`/course`, data);
    return res.data;
  } catch (err) {
    console.log('createCourse catch', err);
  }
};

// 코스 수정
export const updateCourse = async (courseId, data) => {
  try {
    const res = await privateApi.patch(`/course/${courseId}`, data);
    return true;
  } catch (err) {
    console.log('updateCourse catch', err);
  }
};

// 코스 삭제
export const deleteCourse = async (courseId) => {
  try {
    const res = await privateApi.delete(`/course/${courseId}`);
    return true;
  } catch (err) {
    console.log('deleteCourse catch', err);
  }
};

// 코스 좋아요
export const likeCourse = async (courseId) => {
  try {
    const res = await privateApi.post(`/like/course/`, courseId);
    return true;
  } catch (err) {
    console.log('likeCourse catch', err);
  }
};

// 코스 좋아요 취소
export const unlikeCourse = async (courseId) => {
  try {
    const res = await privateApi.post(`/like/cancel/course`, courseId);
    return true;
  } catch (err) {
    console.log('unlikeCourse catch', err);
  }
};
