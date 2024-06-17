import { Select, Space } from 'antd';

function CourseList() {
  return (
    <Space wrap>
      <Select
        defaultValue="최신순"
        style={{ width: 120 }}
        // onChange={handleChange}
        options={[
          { value: '최신순', label: '최신순' },
          { value: '인기순', label: '인기순' },
        ]}
      />
    </Space>
  );
}

export default CourseList;
