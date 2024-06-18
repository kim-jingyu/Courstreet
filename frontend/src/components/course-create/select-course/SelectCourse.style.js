import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const CourseTitle = styled.input`
  display: flex;
  align-items: center;
  height: 60px;
  font-size: 18px;
  font-weight: bold;
  background-color: transparent;
  border: none;
  text-align: center;
`;

export const TitleImage = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
  background-color: ${COLOR.HDLightGray2};
  border: none;
`;

export const CourseContent = styled.textarea`
padding: 50px 30px 10px 30px;
height: 130px;
font-size: 15px;
line-height: 30px;
border: none;
`;
