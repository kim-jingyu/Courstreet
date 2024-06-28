package com.hyundairoad.star.service;

import com.hyundairoad.member.service.MemberService;
import com.hyundairoad.place.service.PlaceService;
import com.hyundairoad.star.domain.MemberPlaceStar;
import com.hyundairoad.star.exception.MemberPlaceStartNotFoundException;
import com.hyundairoad.star.repository.MemberPlaceStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 별점 서비스
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StarService {
    private final MemberPlaceStarRepository memberPlaceStarRepository;
    private final MemberService memberService;
    private final PlaceService placeService;

    /**
     * 회원이 특정 장소에 별점을 추가하거나 업데이트합니다.
     *
     * @param memberId 회원 ID
     * @param placeId 장소 ID
     * @param newRate 새로운 별점
     * @return 처리 결과 (null)
     */
    public Void starPlace(Long memberId, Long placeId, Double newRate) {
        memberPlaceStarRepository.findByMemberIdAndPlaceId(memberId, placeId).ifPresentOrElse(memberPlaceStar -> memberPlaceStar.updateRate(newRate), () -> {
            memberPlaceStarRepository.save(MemberPlaceStar.createMemberPlaceStar(memberService.getMember(memberId), placeService.getPlace(placeId)));
        });
        return null;
    }

    /**
     * 특정 장소의 평균 별점을 조회합니다.
     *
     * @param placeId 장소 ID
     * @return 장소의 평균 별점
     * @throws MemberPlaceStartNotFoundException 해당 장소에 별점이 없는 경우 예외 발생
     */
    public Double getAvgRate(Long placeId) {
        return memberPlaceStarRepository.findAverageRate(placeId).orElseThrow(MemberPlaceStartNotFoundException::new);
    }
}
