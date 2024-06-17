import styled from "styled-components";

export const Tabs = styled.div`
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
  position: relative; /* 자식 요소의 position 설정을 위한 relative */
  &::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    border-bottom: 2px solid #D9D9D9;
    z-index: 1; /* Tab의 border-bottom 아래로 가도록 설정 */
  }
`;

export const Tab = styled.div`
  padding: 10px;
  cursor: pointer;
  font-size: 1rem;
  color: ${props => props.active ? '#00805A' : '#555'};
  border-bottom: ${props => props.active ? '2px solid #00805A' : '2px solid #D9D9D9'};
  z-index: 2; /* Tabs의 border-bottom 위로 오도록 설정 */
  background-color: white; /* 탭의 배경을 흰색으로 설정 */
`;

export const CourseContainer = styled.div`
  display: flex;
  flex-direction: column;
`;