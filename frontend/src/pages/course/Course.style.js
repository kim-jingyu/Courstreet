import styled from 'styled-components';
import active from '/src/assets/icons/course-create-active.png';
import inactive from '/src/assets/icons/course-create-inactive.png';

export const CreateBtn = styled.img`
  position: fixed;
  bottom: 100px;
  right: 15px;

  width: 60px;
  height: 60px;
  z-index: 100;
  content: url(${inactive});
  &:hover {
    content: url(${active});
  }
`;
