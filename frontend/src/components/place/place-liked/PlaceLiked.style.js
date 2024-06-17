import styled from "styled-components";

export const LikePlace = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
`;

export const PlaceImage = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 10px;
  margin-right: 10px;
`;

export const PlaceDetails = styled.div`
  flex-grow: 1;
`;

export const PlaceTitle = styled.div`
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 5px;
`;

export const PlaceRating = styled.div`
  font-size: 0.9rem;
  color: #888;
`;

export const PlaceTag = styled.span`
  padding: 2px 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 0.8rem;
  color: #666;
`;

export const HeartIcon = styled.div`
  font-size: 1.5rem;
  color: red;
`;