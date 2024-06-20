import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Wrapper = styled.div`
  display: flex;
`;

export const Floors = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;

  width: 10%;
  font-size: 13px;
  font-weight: 700;

  background-color: rgba(0, 0, 0, 0.55);
`;

export const Floor = styled.div`
  margin: 0.5px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 25%;

  background-color: ${({ focused }) => (focused === 1 ? 'white' : 'transparent')};
`;
