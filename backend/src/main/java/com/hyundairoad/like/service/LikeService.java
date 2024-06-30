package com.hyundairoad.like.service;

import com.hyundairoad.course.service.CourseService;
import com.hyundairoad.like.domain.MemberCourseLike;
import com.hyundairoad.like.domain.MemberPlaceLike;
import com.hyundairoad.like.repository.MemberCourseLikeRepository;
import com.hyundairoad.like.repository.MemberPlaceLikeRepository;
import com.hyundairoad.like.exception.CannotCancelLikeCourseException;
import com.hyundairoad.like.exception.CannotCancelLikePlaceException;
import com.hyundairoad.like.exception.CannotLikeCourseException;
import com.hyundairoad.like.exception.CannotLikePlaceException;
import com.hyundairoad.member.service.MemberService;
import com.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 좋아요 서비스
 *
 * 작성자: 김진규
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final MemberCourseLikeRepository memberCourseLikeRepository;
    private final MemberPlaceLikeRepository memberPlaceLikeRepository;
    private final MemberService memberService;
    private final CourseService courseService;
    private final PlaceService placeService;

    /**
     * 코스에 좋아요를 추가합니다.
     *
     * @param memberId 회원 ID
     * @param courseId 코스 ID
     * @return 처리 결과 (null)
     * @throws CannotLikeCourseException 좋아요할 수 없는 경우 예외 발생
     */
    public Void likeCourse(Long memberId, Long courseId) {
        memberCourseLikeRepository.findByMemberIdAndCourseId(memberId, courseId).ifPresentOrElse(memberCourseLike -> {
            if (memberCourseLike.getCount() == 1) throw new CannotLikeCourseException();
            memberCourseLike.like();
        }, () -> memberCourseLikeRepository.save(MemberCourseLike.createMemberCourseLike(memberService.getMember(memberId), courseService.getCourse(courseId))));
        return null;
    }

    /**
     * 장소에 좋아요를 추가합니다.
     *
     * @param memberId 회원 ID
     * @param placeId 장소 ID
     * @return 처리 결과 (null)
     * @throws CannotLikePlaceException 좋아요할 수 없는 경우 예외 발생
     */
    public Void likePlace(Long memberId, Long placeId) {
        memberPlaceLikeRepository.findByMemberIdAndPlaceId(memberId, placeId).ifPresentOrElse(memberPlaceLike -> {
            if (memberPlaceLike.getCount() == 1) throw new CannotLikePlaceException();
            memberPlaceLike.like();
        }, () -> memberPlaceLikeRepository.save(MemberPlaceLike.createMemberPlaceLike(memberService.getMember(memberId), placeService.getPlace(placeId))));
        return null;
    }

    /**
     * 코스에 대한 좋아요를 취소합니다.
     *
     * @param memberId 회원 ID
     * @param courseId 코스 ID
     * @return 처리 결과 (null)
     * @throws CannotCancelLikeCourseException 좋아요를 취소할 수 없는 경우 예외 발생
     */
    public Void cancelLikeCourse(Long memberId, Long courseId) {
        MemberCourseLike memberCourseLike = memberCourseLikeRepository.findByMemberIdAndCourseId(memberId, courseId).orElseThrow(CannotCancelLikeCourseException::new);
        if (memberCourseLike.getCount() == 0) throw new CannotCancelLikeCourseException();
        memberCourseLike.cancelLike();
        return null;
    }

    /**
     * 장소에 대한 좋아요를 취소합니다.
     *
     * @param memberId 회원 ID
     * @param placeId 장소 ID
     * @return 처리 결과 (null)
     * @throws CannotCancelLikePlaceException 좋아요를 취소할 수 없는 경우 예외 발생
     */
    public Void cancelLikePlace(Long memberId, Long placeId) {
        MemberPlaceLike memberPlaceLike = memberPlaceLikeRepository.findByMemberIdAndPlaceId(memberId, placeId).orElseThrow(CannotCancelLikePlaceException::new);
        if (memberPlaceLike.getCount() == 0) throw new CannotCancelLikePlaceException();
        memberPlaceLike.cancelLike();
        return null;
    }

    /**
     * 회원이 좋아요한 모든 코스를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원이 좋아요한 모든 코스의 리스트
     */
    public List<MemberCourseLike> getAllLikedCoursesWithMember(Long memberId) {
        return memberCourseLikeRepository.findByMemberId(memberId);
    }

    /**
     * 회원이 좋아요한 모든 장소를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원이 좋아요한 모든 장소의 리스트
     */
    public List<MemberPlaceLike> getAllLikedPlacesWithMember(Long memberId) {
        return memberPlaceLikeRepository.findByMemberId(memberId);
    }
}
