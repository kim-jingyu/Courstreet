import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const ComponentTitle = styled.div`
  font-size: 18px;
  font-weight: 700;
`;

export const NextButton = styled.button`
  margin: 0 auto;
  padding: 8px 70px;
  border-radius: 2px;
  border: none;
  font-size: 14px;
  font-weight: 600;
  background-color: ${COLOR.HDDepartmentGreen};
  color: white;
  &:hover {
    background-color: ${COLOR.HDDarkGreen};
  }
`;