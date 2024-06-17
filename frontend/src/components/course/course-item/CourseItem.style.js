import styled from "styled-components";

export const Container = styled.div`
  max-width: 400px;
  margin: auto;
  padding: 20px;
  font-family: Arial, sans-serif;
`;

export const ItemContainer = styled.div`
  width: 320px;
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  overflow: hidden;
`;

export const ImageGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 1px;
  background-color: #fff;
`;

export const ImageBox = styled.div`
  width: 100%;
  padding-top: 50%;
  background-color: #ccc;
`;

export const ItemFooter = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px 10px 20px;
  margin-top:-15px;
  background-color: #fff;
`;

export const UserContainer = styled.div`
  display: flex; 
  flex-direction: row; 
  justify-content: flex-start; 
  align-items: center;
  margin-bottom: 10px;
`

export const UserIcon = styled.div`
  position: relative;
  top: -20px;
  left: 15px;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: green;
  margin-right: 10px;
`;

export const UserName = styled.span`
  font-size: 0.9rem;
  color: #888;
`;

export const Dot = styled.span`
  margin-left: 10px;
  margin-right: 10px;
`

export const DateRange = styled.span`
  font-size: 0.8rem;
  color: #bbb;
`;

export const ItemTitle = styled.div`
  font-size: 1rem;
  font-weight: bold;
  color: #2d2d2d;
  margin: 5px 0;
`;
