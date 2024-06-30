package com.hyundairoad.mypage.controller;

import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.auth.member.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.course.domain.dto.CourseResponse;
import com.hyundairoad.mypage.service.MyPageService;
import com.hyundairoad.place.domain.PlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 마이페이지 컨트롤러
 *
 * 작성자: 김진규
 */
@Tag(name = "마이페이지", description = "마이페이지 API입니다.")
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    /**
     * 내가 작성한 코스를 조회합니다.
     *
     * @param accessor 회원 정보
     * @return 내가 작성한 코스의 리스트
     */
    @Operation(summary = "내가 작성한 코스를 조회합니다.", description = "회원이 작성한 코스를 조회하는 API입니다.")
    @MemberOnly
    @GetMapping("/course")
    public ResponseEntity<List<CourseResponse>> getMyPageMyCourse(@MemberCheck Accessor accessor) {
        return ResponseEntity.ok().body(myPageService.getMyPageCourseList(accessor.getMemberId()));
    }

    /**
     * 내가 좋아요한 코스를 조회합니다.
     *
     * @param accessor 회원 정보
     * @return 내가 좋아요한 코스의 리스트
     */
    @Operation(summary = "내가 좋아요한 코스를 조회합니다.", description = "회원이 좋아요한 코스를 조회하는 API입니다.")
    @MemberOnly
    @GetMapping("/course/like")
    public ResponseEntity<List<CourseResponse>> getMyPageLikeMyCourse(@MemberCheck Accessor accessor) {
        return ResponseEntity.ok().body(myPageService.getMyPageLikeCourseList(accessor.getMemberId()));
    }

    /**
     * 내가 좋아요한 장소를 조회합니다.
     *
     * @param accessor 회원 정보
     * @return 내가 좋아요한 장소의 리스트
     */
    @Operation(summary = "내가 좋아요한 장소를 조회합니다.", description = "회원이 좋아요한 장소를 조회하는 API입니다.")
    @GetMapping("/place/like")
    public ResponseEntity<List<PlaceResponse>> getMyPageLikeMyPlace(@MemberCheck Accessor accessor) {
        return ResponseEntity.ok().body(myPageService.getMyPageLikePlaceList(accessor.getMemberId()));
    }
}

