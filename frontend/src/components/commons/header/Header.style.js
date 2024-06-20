import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 55px;
  font-size: 1.2rem;
  background-color: white;
  border-bottom: 1px solid #452324;
  z-index: 100;
`;

export const Section = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 3%;
  font-size: 17px;
  font-weight: 700;
  color: ${COLOR.HDDarkBrown};
`;
