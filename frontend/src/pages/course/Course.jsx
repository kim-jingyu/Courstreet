import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import CourseItem from '/src/components/course/course-item/CourseItem';
import TodayPick from '/src/components/course/today-pick/TodayPick';
import { CategorySelector } from '/src/components/course-create/select-category/SelectCategory.style';
import { Select, Space } from 'antd';
import * as S from './Course.style';
import { Input } from 'antd';
import { getAllCourses, getCourses, likeCourse } from '/src/apis/courseAPI';
const { Search } = Input;

function Course() {
  // 네비게이터
  const navigate = useNavigate();
  const goCreate = () => {
    const username = localStorage.getItem('accessToken');
    if (username === null) {
      navigate('/login');
      return;
    }
    navigate('/coursecreate');
  };
  const goDetail = (courseId) => navigate(`/coursedetail/${courseId}`);
  // 데이터
  const [courseDummy, setCourseDummy] = useState([]);
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
  // 코스 검색
  const onSearch = async (value, _e, info) => {
    try {
      const data = await getCourses(value);
      setCourseDummy(data);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };
  // 코스 필터
  useEffect(() => {
    setFilteredCourses(courseDummy.filter((dummy) => currTheme.length === 0 || currTheme.includes(dummy.THEME)));
  }, [courseDummy, currTheme]);
  // 좋아요 누르면 코스 데이터 변경
  const toggleCourseLike = async (course_id) => {
    try {
      const res = await likeCourse(course_id);
      setCourseDummy((prevCourses) =>
        prevCourses.map((course) => (course.COURSE_ID === course_id ? { ...course, LIKED: !course.LIKED } : course)),
      );
    } catch (err) {
      console.log('toggleCourseLike', err);
    }
  };
  // 초기 코스 전체 목록 조회
  useEffect(() => {
    const fetchAllCourses = async () => {
      try {
        const data = await getAllCourses();
        setCourseDummy(data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };
    fetchAllCourses();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error: {error.message}</div>;
  }
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
          {/* 정렬 조건 */}
          <Select
            defaultValue="최신순"
            style={{ width: 90, height: 40 }}
            onChange={handleSort}
            options={[
              { value: 1, label: '최신순' },
              { value: 2, label: '인기순' },
            ]}
          />
          {/* 검색창 */}
          <Search placeholder="" onSearch={onSearch} size="large" style={{ width: '280px' }} />
        </Space>
        <br />

        {filteredCourses.map((course) => (
          <CourseItem
            key={course.COURSE_ID}
            course={course}
            onLikeToggle={toggleCourseLike}
            goDetail={() => goDetail(course.COURSE_ID)}
          />
        ))}

        <S.CreateBtn onClick={goCreate} />
      </div>
    </>
  );
}

export default Course;
