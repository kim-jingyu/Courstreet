import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Wrapper = styled.div`
  display: flex;
`;

export const Order = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  width: 20%;
`;

export const Circle = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  width: 25px;
  height: 25px;

  background-color: ${COLOR.HDDepartmentGreen};
  color: white;
  font-size: 13px;
`;
