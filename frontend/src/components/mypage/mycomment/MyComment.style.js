import styled from 'styled-components';

export const MyCommentTab = styled.div`
  padding: 10px;
  cursor: pointer;
  font-size: 1rem;
  color: #00805a;
  border-bottom: 2px solid #00805a;
  z-index: 2;
  background-color: white;
`;

export const Tabs = styled.div`
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
  position: relative;
  &::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    z-index: 1;
  }
`;

export const Tab = styled.div`
  padding: 10px;
  cursor: pointer;
  font-size: 1rem;
  color: #555;
  z-index: 2;
  background-color: white;
`;

export const CourseContainer = styled.div`
  display: flex;
  flex-direction: column;
`;
