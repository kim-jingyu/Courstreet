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
  margin: 5px;
  padding: 5px 15px;
  border-radius: 20px;
  border: 1px solid ${COLOR.HDDarkBrown};
  background-color: ${({ isselected }) => (isselected === 1 ? COLOR.HDDarkBrown : 'white')};
  color: ${({ isselected }) => (isselected === 1 ? 'white' : COLOR.HDDarkBrown)};
`;

export const CategoryImage = styled.img``;
