import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { courseDummyState } from '/src/recoils/CourseAtoms';

import CourseItem from '/src/components/course/course-item/CourseItem';
import TodayPick from '/src/components/course/today-pick/TodayPick';

import { Section, CategorySelector } from '/src/components/course-create/select-category/SelectCategory.style';
import { Select, Space } from 'antd';
import * as S from './Course.style';

import { Input } from 'antd';
import LikePost from '/src/components/mypage/like-post/LikePost';
import CourseLikeItem from '/src/components/course/course-like-item/CourseLikeItem';
const { Search } = Input;
import courseAPI from '/src/api/course/courseAPI.jsx';

function Course() {
  const [courseDummy, setCourseDummy] = useRecoilState(courseDummyState);

  const navigate = useNavigate();
  const goCreate = () => {
    const username = localStorage.getItem('username');
    if (username === null) {
      navigate('/login')
      return
    }
    navigate('/coursecreate');
  };
  const goDetail = (courseId) => navigate(`/coursedetail/${courseId}`);

  const handleChange = (value) => console.log(`selected ${value}`);

  const [currTheme, setCurrTheme] = useState([]);
  const pickTheme = (val) => {
    currTheme.includes(val) ? setCurrTheme(currTheme.filter((e) => e != val)) : setCurrTheme([...currTheme, val]);
  };

  // const [contents, setContents] = useState([]);
  // useEffect(() => {
  //   courseAPI(1).then(response => {
  //     setContents(response.data);
  //     }).catch(error => {
  //         console.error('Error fetching the hello message:', error);
  //     });
  // }, []);

  return (
    <>
      {/* <CourseLikeItem /> */}
      <TodayPick />

      <div style={{ display: 'flex', flexDirection: 'column' }}>
        <div style={{ overflowX: 'auto' }}>
          <div
            style={{
              display: 'flex',
              width: '860px',
              height: '58px',
              margin: '10px 0 0 -10px',
            }}
          >
            <CategorySelector isselected={+currTheme.includes(1)} onClick={() => pickTheme(1)}>
              #SNS 핫플레이스
            </CategorySelector>
            <CategorySelector isselected={+currTheme.includes(2)} onClick={() => pickTheme(2)}>
              #쇼핑은 열정적으로
            </CategorySelector>
            <CategorySelector isselected={+currTheme.includes(3)} onClick={() => pickTheme(3)}>
              #맛있는 미식의 경험
            </CategorySelector>
            <CategorySelector isselected={+currTheme.includes(4)} onClick={() => pickTheme(4)}>
              #카페인 중독
            </CategorySelector>
            <CategorySelector isselected={+currTheme.includes(5)} onClick={() => pickTheme(5)}>
              #쇼핑이 좋아요
            </CategorySelector>
          </div>
        </div>

        <Space wrap style={{ display: 'flex', justifyContent: 'space-between', marginTop: '5px' }}>
          {/* 정렬 조건 */}
          <Select
            defaultValue="최신순"
            style={{ width: 90, height: 40 }}
            onChange={handleChange}
            options={[
              { value: 1, label: '최신순' },
              { value: 2, label: '인기순' },
            ]}
          />
          {/* 검색창 */}
          <Search placeholder="" onChange={(e) => onSearch(e.target.value)} size="large" style={{ width: '280px' }} />
        </Space>
        <br />

        {courseDummy.map((course) => (
          <CourseItem key={course.COURSE_ID} course={course} goDetail={() => goDetail(course.COURSE_ID)} />
        ))}

        <S.CreateBtn onClick={goCreate} />
      </div>
    </>
  );
}

export default Course;
