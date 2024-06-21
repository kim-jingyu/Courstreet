import styled from 'styled-components';

export const PostButton = styled.button`
  padding: 5px 10px;
  margin: 0 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #555;
  color: white;
  background-color: ${props => props.active ? '#00805A' : '#f0f0f0'};
  color: ${props => props.active ? '#f0f0f0' : '#000000'};
`;

export const PlaceButton = styled.button`
  padding: 5px 10px;
  margin: 0 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #00805a;
  color: white;
  background-color: ${props => props.active ? '#00805A' : '#f0f0f0'};
  color: ${props => props.active ? '#f0f0f0' : '#000000'};

`;
