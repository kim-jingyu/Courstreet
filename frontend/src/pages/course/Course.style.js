import styled from 'styled-components';
import active from '/src/assets/icons/course-create-active.png';
import homeCreate from '/src/assets/icons/home-create.png';

export const CreateBtn = styled.img`
  position: fixed;
  bottom: 40px;
  right: 15px;

  height: 50px;
  z-index: 100;
  content: url(${homeCreate});
`;
