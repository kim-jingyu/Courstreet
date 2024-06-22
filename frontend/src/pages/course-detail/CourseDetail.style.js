import styled from 'styled-components';
import COLOR from '/src/constants/color';

export const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`

export const CourseTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 5px;
  width: 300px;
  height: 60px;
  font-size: 19px;
  font-weight: bold;
`;

export const TitleImage = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;
  background-color: ${COLOR.HDLightGray2};
  border: none;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지를 박스에 맞게 채움 */
  }
`;

export const CourseContent = styled.div`
margin-bottom: 20px;
padding: 50px 30px 50px 30px;
width: 300px;
font-size: 16px;
line-height: 30px;
`;
