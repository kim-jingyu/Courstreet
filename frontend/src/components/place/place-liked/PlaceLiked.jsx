import './PlaceLiked.style'
import { LikePlace, HeartIcon, PlaceDetails, PlaceImage, PlaceRating, PlaceTag, PlaceTitle } from './PlaceLiked.style';
import StarbucksImg from '/src/assets/starbucks.png'

function PlaceLiked() {
    return (
        <LikePlace>
            <PlaceImage src={StarbucksImg} alt="Starbucks" />
            <PlaceDetails>
            <PlaceTitle>스타벅스(Starbucks)</PlaceTitle>
            <PlaceRating>★ 3.0</PlaceRating>
            <PlaceTag>카페</PlaceTag>
            </PlaceDetails>
            <HeartIcon>❤️</HeartIcon>
      </LikePlace>
    )
}

export default PlaceLiked;