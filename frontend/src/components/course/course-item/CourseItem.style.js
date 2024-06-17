import styled from "styled-components";

export const CourseItemWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
`;

export const CourseHeader = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
`;

export const CourseImage = styled.div`
  width: 60px;
  height: 60px;
  background: #ccc;
  border-radius: 10px;
  margin-right: 10px;
`;

export const CourseDetails = styled.div`
  flex-grow: 1;
`;

export const UserName = styled.div`
  font-size: 0.9rem;
  color: #888;
`;

export const DateRange = styled.div`
  font-size: 0.8rem;
  color: #bbb;
`;

export const CourseTitle = styled.div`
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 5px;
`;

export const CourseContent = styled.div`
  font-size: 0.9rem;
  color: #666;
`;
