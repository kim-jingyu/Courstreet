package hyundairoad.hyundairoad.place.service;

import hyundairoad.hyundairoad.image.service.ImageService;
import hyundairoad.hyundairoad.member.domain.like.MemberPlaceLike;
import hyundairoad.hyundairoad.member.repository.MemberPlaceLikeRepository;
import hyundairoad.hyundairoad.place.domain.Place;
import hyundairoad.hyundairoad.place.domain.PlaceResponse;
import hyundairoad.hyundairoad.place.domain.dto.CreatePlaceRequest;
import hyundairoad.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import hyundairoad.hyundairoad.place.exception.PlaceNotFoundException;
import hyundairoad.hyundairoad.place.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final MemberPlaceLikeRepository memberPlaceLikeRepository;
    private final ImageService imageService;

    public List<PlaceResponse> getAllPlaces() throws MalformedURLException {
        List<PlaceResponse> placeResponseList = new ArrayList<>();
        for (Place place : placeRepository.findAll()) addToResponseList(place, placeResponseList);
        return placeResponseList;
    }

    public List<PlaceResponse> getSearchedPlaces(String placeName) throws MalformedURLException {
        List<PlaceResponse> placeResponseList = new ArrayList<>();
        for (Place place : placeRepository.findByNameContaining(placeName)) addToResponseList(place, placeResponseList);
        return placeResponseList;
    }

    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(PlaceNotFoundException::new);
    }

    public Void createPlace(CreatePlaceRequest createPlaceRequest) throws IOException {
        placeRepository.save(Place.createPlace(createPlaceRequest, imageService.uploadFile(createPlaceRequest.image())));
        return null;
    }

    public Void updatePlace(UpdatePlaceRequest updatePlaceRequest) throws IOException {
        Place place = placeRepository.findById(updatePlaceRequest.placeId()).orElseThrow(PlaceNotFoundException::new);
        place.updatePlace(updatePlaceRequest, imageService.updateFile(place.getPlaceImgUrl(), updatePlaceRequest.image()));
        return null;
    }

    public List<PlaceResponse> getLikedPlacesByMemberId(Long memberId) throws MalformedURLException {
        List<PlaceResponse> placeResponseList = new ArrayList<>();
        for (MemberPlaceLike memberPlaceLike : memberPlaceLikeRepository.findByMemberId(memberId)) {
            addToResponseList(memberPlaceLike.getPlace(), placeResponseList);
        }
        return placeResponseList;
    }

    private void addToResponseList(Place place, List<PlaceResponse> placeResponseList) throws MalformedURLException {
        MemberPlaceLike memberPlaceLike = getMemberPlaceLike(place);
        Resource image = getImage(place);
        if (checkLiked(place, memberPlaceLike, placeResponseList, image)) return;
        placeResponseList.add(PlaceResponse.of(place, memberPlaceLike.getMember().getId(), image, memberPlaceLikeRepository.findAverageRate(), memberPlaceLike.getCount()));
    }

    private Resource getImage(Place place) throws MalformedURLException {
        return imageService.getImage(place.getPlaceImgUrl());
    }

    private MemberPlaceLike getMemberPlaceLike(Place place) {
        return memberPlaceLikeRepository.findByPlaceId(place.getId());
    }

    private boolean checkLiked(Place place, MemberPlaceLike memberPlaceLike, List<PlaceResponse> placeResponseList, Resource image) {
        if (memberPlaceLike == null || memberPlaceLike.getCount() == 0) {
            placeResponseList.add(PlaceResponse.of(place, null, image, null, null));
            return true;
        }
        return false;
    }
}
