import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Section = styled.div`
  display: flex;
  flex-wrap: wrap;
  display: flex;
`;

export const CategorySelector = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 10px;
  padding: 6px 15px;
  border-radius: 20px;
  border: 1.5px solid ${COLOR.HDDarkBrown};
  background-color: ${({ isselected }) => (isselected === 1 ? COLOR.HDDarkBrown : 'white')};
  color: ${({ isselected }) => (isselected === 1 ? 'white' : COLOR.HDDarkBrown)};

  font-weight: 600;
`;
