package com.hyundairoad.mypage.service;

import com.hyundairoad.course.domain.dto.CourseResponse;
import com.hyundairoad.course.service.CourseService;
import com.hyundairoad.like.service.LikeService;
import com.hyundairoad.place.domain.PlaceResponse;
import com.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 마이페이지 서비스
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPageService {
    private final CourseService courseService;
    private final PlaceService placeService;
    private final LikeService likeService;

    /**
     * 회원이 작성한 모든 코스를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원이 작성한 모든 코스의 리스트
     */
    public List<CourseResponse> getMyPageCourseList(Long memberId) {
        return courseService.getAllCoursesWithMember(memberId);
    }

    /**
     * 회원이 좋아요한 모든 코스를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원이 좋아요한 모든 코스의 리스트
     */
    public List<CourseResponse> getMyPageLikeCourseList(Long memberId) {
        return likeService.getAllLikedCoursesWithMember(memberId).stream()
                .map(memberCourseLike -> CourseResponse.of(courseService.getCourse(memberCourseLike.getCourse().getId())))
                .toList();
    }

    /**
     * 회원이 좋아요한 모든 장소를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원이 좋아요한 모든 장소의 리스트
     */
    public List<PlaceResponse> getMyPageLikePlaceList(Long memberId) {
        return likeService.getAllLikedPlacesWithMember(memberId).stream()
                .map(memberPlaceLike -> PlaceResponse.of(placeService.getPlace(memberPlaceLike.getPlace().getId())))
                .toList();
    }
}
