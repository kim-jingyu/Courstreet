package com.hyundairoad.hyundairoad.place.service;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarLikeDTO;
import com.hyundairoad.hyundairoad.place.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceMapper placeMapper;

    /**
     * 장소 등록
     * @param place
     */
    public void register(Place place) {
        placeMapper.insert(place);
    }

    /**
     * 장소 삭제
     * @param placeId
     */
    public void remove(Long placeId) {
        placeMapper.delete(placeId);
    }

    /**
     * 장소 수정
     * @param place
     */
    @Transactional
    public void update(Place place) {
        placeMapper.update(place);
    }

    /**
     * 장소 전체 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<Place> getAllList() {
        return placeMapper.selectAllPlaceList();
    }

    /**
     * 전체 장소 + 별점 + liked 여부 조회
     * 로그인하지 않은 사용자는 memberid = null
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public List<PlaceStarLikeDTO> getAllLikedPlacesByMemberId(Long memberId) {
        return placeMapper.selectAllPlaceStarList(memberId);
    }


    /**
     * 특정 사용자가 좋아요 한 장소만 보기
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public List<LikedPlaceDTO> getLikedPlacesByMemberId(Long memberId) {
        return placeMapper.selectPlaceStarByMemberId(memberId);
    }

}
