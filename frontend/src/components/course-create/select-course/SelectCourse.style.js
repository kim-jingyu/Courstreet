import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const CourseTitle = styled.input`
  display: flex;
  align-items: center;
  margin-bottom: 5px;
  width: 300px;
  height: 60px;
  font-size: 19px;
  font-weight: bold;
  background-color: transparent;
  border: none;
  text-align: center;
`;

export const TitleImage = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;
  background-color: ${COLOR.HDLightGray2};
  border: none;
`;

export const CourseContent = styled.textarea`
margin-bottom: 20px;
padding: 50px 30px 10px 30px;
width: 300px;
height: 130px;
font-size: 15px;
line-height: 30px;
border: none;
`;
