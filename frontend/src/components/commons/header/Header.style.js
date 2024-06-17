import styled from 'styled-components';

export const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 70px;
  font-size: 1.2rem;
  background-color: white;
  border-bottom: 1px solid #452324;
  z-index: 100;
`;

export const SectionPrev = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 3%;
`;

export const SectionPost = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 3%;
`;
