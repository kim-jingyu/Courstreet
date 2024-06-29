import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllCourses, searchCourses } from '/src/apis/courseAPI';
import CourseItem from '/src/components/course/course-item/CourseItem';
import TodayPick from '/src/components/course/today-pick/TodayPick';
import { CategorySelector } from '/src/components/course-create/select-category/SelectCategory.style';
import { Select, Space } from 'antd';
import * as S from './Course.style';
import { Input } from 'antd';
const { Search } = Input;

function Course() {
  // 네비게이터
  const navigate = useNavigate();
  const goCreate = () => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken === null) {
      navigate('/login');
      return;
    }
    navigate('/coursecreate');
  };
  const goDetail = (courseId) => navigate(`/coursedetail/${courseId}`);
  // 데이터
  const [courses, setCourses] = useState([]);
  const [filteredCourses, setFilteredCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  // 정렬 조건
  const handleSort = (value) => console.log(`selected ${value}`);
  // 테마 선택
  const [currTheme, setCurrTheme] = useState([]);
  const pickTheme = (val) => {
    currTheme.includes(val) ? setCurrTheme(currTheme.filter((e) => e != val)) : setCurrTheme([...currTheme, val]);
  };
  // // 코스 필터
  useEffect(() => {
    setFilteredCourses(courses.filter((dummy) => currTheme.length === 0 || currTheme.includes(dummy.theme)));
  }, [courses, currTheme]);
  // 코스 검색
  const onSearch = async (keyword) => {
    if (keyword.trim().length === 0) return;
    const fetchSearchCourses = async (keyword) => {
      try {
        const data = await searchCourses(keyword);
        setCourses(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchSearchCourses(keyword);
  };
  // 코스 전체 목록 조회
  useEffect(() => {
    const fetchAllCourses = async () => {
      try {
        const data = await getAllCourses();
        setCourses(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchAllCourses();
  }, []);

  return (
    <>
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
            <CategorySelector
              isselected={+currTheme.includes('SNS 핫플레이스')}
              onClick={() => pickTheme('SNS 핫플레이스')}
            >
              #SNS 핫플레이스
            </CategorySelector>
            <CategorySelector
              isselected={+currTheme.includes('쇼핑은 열정적으로')}
              onClick={() => pickTheme('쇼핑은 열정적으로')}
            >
              #쇼핑은 열정적으로
            </CategorySelector>
            <CategorySelector
              isselected={+currTheme.includes('맛있는 미식의 경험')}
              onClick={() => pickTheme('맛있는 미식의 경험')}
            >
              #맛있는 미식의 경험
            </CategorySelector>
            <CategorySelector isselected={+currTheme.includes('카페인 중독')} onClick={() => pickTheme('카페인 중독')}>
              #카페인 중독
            </CategorySelector>
            <CategorySelector
              isselected={+currTheme.includes('도심 속 힐링')}
              onClick={() => pickTheme('도심 속 힐링')}
            >
              #도심 속 힐링
            </CategorySelector>
          </div>
        </div>

        <Space wrap style={{ display: 'flex', justifyContent: 'space-between', marginTop: '5px' }}>
          <Select
            defaultValue="최신순"
            style={{ width: 90, height: 40 }}
            onChange={(e) => handleSort(e.target.value)}
            options={[
              { value: 1, label: '최신순' },
              { value: 2, label: '인기순' },
            ]}
          />
          <Search placeholder="" onSearch={onSearch} size="large" style={{ width: '280px' }} />
        </Space>
        <br />
        {loading ? (
          <div>Loading...</div>
        ) : error ? (
          <div>Error: {error.message}</div>
        ) : (
          filteredCourses.map((course) => (
            <CourseItem
              key={course.courseId}
              course={course}
              setCourses={setCourses}
              goDetail={() => goDetail(course.courseId)}
            />
          ))
        )}

        <S.CreateBtn onClick={goCreate} />
      </div>
    </>
  );
}

export default Course;
