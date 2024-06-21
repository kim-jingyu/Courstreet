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
  padding: 10px 0px;
`;

export const Circle = styled.div`
  width: 25px;
  height: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;

  background-color: ${COLOR.HDDepartmentGreen};
  color: white;
  font-size: 13px;
`;

export const Line = styled.div`
  height: 70px;
  border: 1px solid ${COLOR.HDLigthGray1};
`;

export const Content = styled.textarea`
  margin: -5px 0 30px 0;
  height: 50px;
  border: 1px solid ${COLOR.HDLigthGray1};
  font-size: 15px;
  font-weight: 500;
`;

export const TrashButton = styled.img`
  margin: 8px 0 auto 0;
  width: 19px;
  height: 19px;
`;

export const AddButton = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
`