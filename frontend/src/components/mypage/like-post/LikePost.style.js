import styled from 'styled-components';

export const Tabs = styled.div`
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
`;

export const Tab = styled.div`
  padding: 10px;
  cursor: pointer;
  font-size: 1rem;
  color: ${props => props.active ? '#00805a' : '#555'};
  border-bottom: ${props => props.active ? '2px solid #00805a' : 'none'};
`;

export const LikeTab = styled.div`
  padding: 10px;
  cursor: pointer;
  font-size: 1rem;
  color: #00805a;
  color: ${props => props.active ? '#00805a' : '#555'};
  border-bottom: ${props => props.active ? '2px solid #00805a' : 'none'};

`;

export const FilterContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
`;

export const PostButton = styled.button`
  padding: 5px 10px;
  margin: 0 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #00805a;
  color: white;
`;

export const PlaceButton = styled.button`
  padding: 5px 10px;
  margin: 0 5px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #555;
  color: white;
`;

export const LikeContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const LikeItem = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
`;

export const ItemHeader = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
`;

export const ItemImage = styled.div`
  width: 60px;
  height: 60px;
  background: #ccc;
  border-radius: 10px;
  margin-right: 10px;
`;

export const ItemDetails = styled.div`
  flex-grow: 1;
`;

export const UserName = styled.div`
  font-size: 0.9rem;
  color: #888;
`;

export const DateRange = styled.div`
  font-size: 0.8rem;
  color: #bbb;
`;

export const ItemTitle = styled.div`
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 5px;
`;

export const ItemContent = styled.div`
  font-size: 0.9rem;
  color: #666;
`;

export const HeartIcon = styled.div`
  font-size: 1.5rem;
  color: red;
  margin-left: auto;
`;
