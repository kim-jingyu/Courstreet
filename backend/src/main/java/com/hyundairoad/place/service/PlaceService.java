package com.hyundairoad.place.service;

import com.hyundairoad.image.service.ImageService;
import com.hyundairoad.member.domain.like.MemberPlaceLike;
import com.hyundairoad.member.repository.MemberPlaceLikeRepository;
import com.hyundairoad.member.repository.MemberPlaceStarRepository;
import com.hyundairoad.place.domain.Place;
import com.hyundairoad.place.domain.PlaceResponse;
import com.hyundairoad.place.domain.dto.CreatePlaceRequest;
import com.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import com.hyundairoad.place.exception.PlaceNotFoundException;
import com.hyundairoad.place.repository.PlaceRepository;
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
    private final MemberPlaceStarRepository memberPlaceStarRepository;
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
        List<MemberPlaceLike> memberPlaceLikeList = getMemberPlaceLike(place.getId());
        Resource image = getImage(place);
        for (MemberPlaceLike memberPlaceLike : memberPlaceLikeList) {
            Long memberId = memberPlaceLike.getMember().getId();
            Long placeId = place.getId();
            if (checkLiked(place, memberPlaceLike, placeResponseList, image)) return;
            placeResponseList.add(PlaceResponse.of(place, memberId, image, memberPlaceStarRepository.findAverageRate(placeId), memberPlaceStarRepository.findByPlaceIdAndMemberId(placeId, memberId).getRate()));
        }
    }

    private Resource getImage(Place place) throws MalformedURLException {
        return imageService.getImage(place.getPlaceImgUrl());
    }

    private List<MemberPlaceLike> getMemberPlaceLike(Long place) {
        return memberPlaceLikeRepository.findByPlaceId(place);
    }

    private boolean checkLiked(Place place, MemberPlaceLike memberPlaceLike, List<PlaceResponse> placeResponseList, Resource image) {
        if (memberPlaceLike == null || memberPlaceLike.getCount() == 0) {
            placeResponseList.add(PlaceResponse.of(place, null, image, null, null));
            return true;
        }
        return false;
    }
}
