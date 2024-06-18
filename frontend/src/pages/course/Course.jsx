import CourseItem from '/src/components/course/course-item/CourseItem';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TodayPick from '/src/components/course/today-pick/TodayPick';
import { Select, Space } from 'antd';
import * as S from './Course.style';
import { Section, CategorySelector } from '/src/components/course-create/select-category/SelectCategory.style';

function Course() {
  const navigate = useNavigate();
  const goCreate = () => navigate('/coursecreate');

  const handleChange = (value) => console.log(`selected ${value}`);
  
  const [currTheme, setCurrTheme] = useState([]);
  const pickTheme = (val) => {
    currTheme.includes(val)
    ? setCurrTheme(currTheme.filter((e) => e != val))
    : setCurrTheme([...currTheme, val]);
  };

  const [contents, setContents] = useState([1, 2, 3, 4, 5]);

  return (
    <>
      <TodayPick />

      <Space wrap>
        <Select
          defaultValue="최신순"
          style={{ width: 120 }}
          onChange={handleChange}
          options={[
            { value: 1, label: '최신순' },
            { value: 2, label: '인기순' },
          ]}
        />
      </Space>
      <br />

      <Section>
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
      </Section>

      {contents.map((v, i) => (
        <CourseItem key={i} />
      ))}

      <S.CreateBtn onClick={goCreate} />
    </>
  );
}

export default Course;
