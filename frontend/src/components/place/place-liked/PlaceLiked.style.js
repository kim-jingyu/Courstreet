import styled from 'styled-components';

export const HeartIcon = styled.img`
  width: 15px;
  height: 15px;
  margin-bottom: 52px;
  margin-right: 20px;
`;

export const LikeContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export const LikeItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 2px 10px;
  border: 1px solid #ccc;
  box-shadow: 0px 4px 4px 0px #a8a9ad;
  border-radius: 10px;
  background: white;
`;

export const ItemImage = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 10px;
  margin-right: 10px;
`;

export const ItemDetails = styled.div`
  flex-grow: 1;
  padding: 12px;
`;

export const ItemTitle = styled.div`
  font-size: 0.9rem;
  font-weight: bold;
  margin-bottom: 5px;
`;

export const ItemRating = styled.div`
  font-size: 0.7rem;
  color: #888;
  display: flex;
  align-items: center;
`;

export const ItemTag = styled.span`
  padding: 2px 5px;
  border: 1px solid;
  border-radius: 5px;
  font-size: 0.7rem;
  color: #f0ad4e;
  margin-left: 10px;
`;

export const FooterDetails = styled.div`
  font-size: 0.6rem;
  color: #888;
  margin-top: 5px;
`;
