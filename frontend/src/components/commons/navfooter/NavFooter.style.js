import styled from 'styled-components';

export const Container = styled.div`
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 80px;
  background-color: black;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.2rem;
  z-index: 100;
`;

export const Section = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
`;

export const SectionImage = styled.img`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30%;
  // width: 100%;
`;

export const SectionText = styled.div`
  margin-top: 3%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30%;
  width: 100%;
  font-size: 13px;
  font-weight: 600;
`;
