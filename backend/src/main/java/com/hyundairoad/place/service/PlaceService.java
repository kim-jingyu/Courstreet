package com.hyundairoad.place.service;

import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.image.service.ImageService;
import com.hyundairoad.member.repository.MemberPlaceLikeRepository;
import com.hyundairoad.member.repository.MemberPlaceStarRepository;
import com.hyundairoad.place.domain.Place;
import com.hyundairoad.place.domain.PlaceResponse;
import com.hyundairoad.place.domain.dto.CreatePlaceRequest;
import com.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import com.hyundairoad.place.exception.PlaceNotFoundException;
import com.hyundairoad.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 장소 서비스
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final MemberPlaceLikeRepository memberPlaceLikeRepository;
    private final MemberPlaceStarRepository memberPlaceStarRepository;
    private final ImageService imageService;

    /**
     * 회원이 장소를 소유하고 있는지 검증합니다.
     *
     * @param memberId 회원 ID
     * @param placeId 장소 ID
     * @throws AuthException 회원이 장소를 소유하지 않는 경우 예외 발생
     */
    public void validatePlaceByMember(Long memberId, Long placeId) {
        if (!placeRepository.existsByMemberIdAndId(memberId, placeId)) {
            throw new AuthException();
        }
    }

    /**
     * 모든 장소를 조회합니다.
     *
     * @return 모든 장소의 리스트
     */
    public List<PlaceResponse> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(PlaceResponse::of)
                .toList();
    }

    /**
     * 특정 이름을 포함한 장소를 검색합니다.
     *
     * @param placeName 장소 이름
     * @return 특정 이름을 포함한 장소의 리스트
     */
    public List<PlaceResponse> getSearchedPlaces(String placeName) {
        return placeRepository.findByNameContaining(placeName).stream()
                .map(PlaceResponse::of)
                .toList();
    }

    /**
     * 새로운 장소를 생성합니다.
     *
     * @param createPlaceRequest 장소 생성 요청 정보
     * @return 생성된 장소의 ID
     * @throws IOException 이미지 파일 처리 중 예외 발생
     */
    public Long createPlace(CreatePlaceRequest createPlaceRequest) throws IOException {
        return placeRepository.save(Place.createPlace(createPlaceRequest, imageService.uploadFile(createPlaceRequest.image()))).getId();
    }

    /**
     * 특정 장소를 수정합니다.
     *
     * @param updatePlaceRequest 장소 수정 요청 정보
     * @return 수정 결과 (null)
     * @throws IOException 이미지 파일 처리 중 예외 발생
     */
    public Void updatePlace(UpdatePlaceRequest updatePlaceRequest) throws IOException {
        Place place = getPlace(updatePlaceRequest.placeId());
        place.updatePlace(updatePlaceRequest, imageService.updateFile(place.getPlaceImgUrl(), updatePlaceRequest.image()));
        return null;
    }

    /**
     * 특정 장소를 조회합니다.
     *
     * @param placeId 장소 ID
     * @return 특정 장소의 상세 정보
     * @throws PlaceNotFoundException 장소를 찾을 수 없는 경우 예외 발생
     */
    public Place getPlace(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(PlaceNotFoundException::new);
    }
}
