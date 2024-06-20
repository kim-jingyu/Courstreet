import instance from "../api";

export const getAllPlace = () => {
  return instance.get('/api/place', {});
};

export const getLikePlace = () => {
  return instance.get('/api/place/mylike', {});
};

export const getPlaceDetail = (placeId) => {
  return instance.get('/api/place/' + placeId, {
  });
};