package com.hyundairoad.like.controller;

import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.auth.member.MemberOnly;
import com.hyundairoad.course.service.CourseService;
import com.hyundairoad.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 좋아요 컨트롤러
 *
 * 작성자: 김진규
 */
@Tag(name = "좋아요", description = "좋아요 API입니다.")
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final CourseService courseService;

    /**
     * 코스에 좋아요를 추가합니다.
     *
     * @param accessor 회원 정보
     * @param courseId 좋아요를 추가할 코스의 ID
     * @return 처리 결과
     */
    @Operation(summary = "코스에 좋아요를 추가합니다.", description = "코스에 좋아요를 추가하는 API입니다.")
    @MemberOnly
    @PostMapping("/course")
    public ResponseEntity<Void> likeCourse(@MemberCheck Accessor accessor, @RequestBody Long courseId) {
        Long memberId = getMemberIdWithCourse(accessor, courseId);
        return ResponseEntity.ok().body(likeService.likeCourse(memberId, courseId));
    }

    /**
     * 장소에 좋아요를 추가합니다.
     *
     * @param accessor 회원 정보
     * @param placeId 좋아요를 추가할 장소의 ID
     * @return 처리 결과
     */
    @Operation(summary = "장소에 좋아요를 추가합니다.", description = "장소에 좋아요를 추가하는 API입니다.")
    @MemberOnly
    @PostMapping("/place")
    public ResponseEntity<Void> likePlace(@MemberCheck Accessor accessor, @RequestBody Long placeId) {
        Long memberId = accessor.getMemberId();
        return ResponseEntity.ok().body(likeService.likePlace(memberId, placeId));
    }

    /**
     * 코스에 대한 좋아요를 취소합니다.
     *
     * @param accessor 회원 정보
     * @param courseId 좋아요를 취소할 코스의 ID
     * @return 처리 결과
     */
    @Operation(summary = "코스에 대한 좋아요를 취소합니다.", description = "코스에 대한 좋아요를 취소하는 API입니다.")
    @MemberOnly
    @PostMapping("/cancel/course")
    public ResponseEntity<Void> cancelLikeCourse(@MemberCheck Accessor accessor, @RequestBody Long courseId) {
        Long memberId = getMemberIdWithCourse(accessor, courseId);
        return ResponseEntity.ok().body(likeService.cancelLikeCourse(memberId, courseId));
    }

    /**
     * 장소에 대한 좋아요를 취소합니다.
     *
     * @param accessor 회원 정보
     * @param placeId 좋아요를 취소할 장소의 ID
     * @return 처리 결과
     */
    @Operation(summary = "장소에 대한 좋아요를 취소합니다.", description = "장소에 대한 좋아요를 취소하는 API입니다.")
    @MemberOnly
    @PostMapping("/cancel/place")
    public ResponseEntity<Void> cancelLikePlace(@MemberCheck Accessor accessor, @RequestBody Long placeId) {
        Long memberId = accessor.getMemberId();
        return ResponseEntity.ok().body(likeService.cancelLikePlace(memberId, placeId));
    }

    private Long getMemberIdWithCourse(Accessor accessor, Long courseId) {
        Long memberId = accessor.getMemberId();
        courseService.validateCourseByMember(memberId, courseId);
        return memberId;
    }
}
