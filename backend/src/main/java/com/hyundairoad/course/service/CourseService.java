package com.hyundairoad.course.service;

import com.hyundairoad.course.domain.Course;
import com.hyundairoad.course.domain.CoursePlace;
import com.hyundairoad.course.domain.dto.CourseCreateRequest;
import com.hyundairoad.course.domain.dto.CourseResponse;
import com.hyundairoad.course.domain.dto.CourseUpdateRequest;
import com.hyundairoad.course.domain.dto.PlacePerMemo;
import com.hyundairoad.course.exception.CourseNotFoundException;
import com.hyundairoad.course.exception.CoursePlaceNotFoundException;
import com.hyundairoad.course.repository.CoursePlaceRepository;
import com.hyundairoad.course.repository.CourseRepository;
import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.image.service.ImageService;
import com.hyundairoad.member.domain.Member;
import com.hyundairoad.like.repository.MemberCourseLikeRepository;
import com.hyundairoad.member.service.MemberService;
import com.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 코스 서비스
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final MemberCourseLikeRepository memberCourseLikeRepository;
    private final PlaceService placeService;
    private final ImageService imageService;
    private final MemberService memberService;

    /**
     * 회원이 코스를 소유하고 있는지 검증합니다.
     *
     * @param memberId 회원 ID
     * @param courseId 코스 ID
     * @throws AuthException 회원이 코스를 소유하지 않는 경우 예외 발생
     */
    public void validateCourseByMember(Long memberId, Long courseId) {
        if (!courseRepository.existsByMemberIdAndId(memberId, courseId)) {
            throw new AuthException();
        }
    }

    /**
     * 모든 코스를 조회합니다.
     *
     * @return 모든 코스의 리스트
     */
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> CourseResponse.of(course, getNickName(course), getMemberId(course)))
                .toList();
    }

    /**
     * 회원과 관련된 모든 코스를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원과 관련된 모든 코스의 리스트
     */
    public List<CourseResponse> getAllCoursesWithMember(Long memberId) {
        return courseRepository.findByMemberId(memberId).stream()
                .map(course -> CourseResponse.of(course, getNickName(course), getMemberId(course), course.getMemberCourseLikeList().stream()
                        .anyMatch(memberCourseLike -> memberCourseLike.getMember().getId().equals(memberId))))
                .toList();
    }

    /**
     * 특정 코스를 조회합니다.
     *
     * @param id 코스 ID
     * @return 특정 코스의 상세 정보
     * @throws CourseNotFoundException 코스를 찾을 수 없는 경우 예외 발생
     */
    public CourseResponse getCourseDetail(Long id) {
        return CourseResponse.of(getCourse(id));
    }

    /**
     * 회원과 관련된 특정 코스를 조회합니다.
     *
     * @param courseId 코스 ID
     * @param memberId 회원 ID
     * @return 회원과 관련된 특정 코스의 상세 정보
     * @throws CourseNotFoundException 코스를 찾을 수 없는 경우 예외 발생
     */
    public CourseResponse getCourseDetailWithMember(Long courseId, Long memberId) {
        Course course = courseRepository.findByIdAndMemberId(courseId, memberId);
        return CourseResponse.of(course, course.getMemberCourseLikeList().stream()
                .anyMatch(memberCourseLike -> memberCourseLike.getMember().getId().equals(memberId)));
    }

    /**
     * 특정 코스를 삭제합니다.
     *
     * @param id 코스 ID
     * @return 삭제 결과 (null)
     */
    @Transactional
    public Void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return null;
    }

    /**
     * 키워드를 기반으로 코스를 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 검색된 코스의 리스트
     */
    public List<CourseResponse> searchCourse(String keyword) {
        return courseRepository.findByTitleOrContentContaining(keyword).stream()
                .map(CourseResponse::of)
                .toList();
    }

    /**
     * 특정 코스를 수정합니다.
     *
     * @param courseId 코스 ID
     * @param courseUpdateRequest 코스 수정 요청 정보
     * @return 수정 결과 (null)
     * @throws IOException 이미지 파일 처리 중 예외 발생
     */
    @Transactional
    public Void updateCourse(Long courseId, Long memberId, CourseUpdateRequest courseUpdateRequest) throws IOException {
        Course course = getCourse(courseId);
        String newImgUrl = imageService.updateFile(course.getCourseImgUrl(), courseUpdateRequest.image());
        Member member = memberService.getMember(memberId);
        courseUpdateRequest.placePerMemos()
                        .forEach(placePerMemo -> {
                            Long placeId = getPlaceId(placePerMemo);
                            getCoursePlace(courseId, placeId).update(placeService.getPlace(placeId), placePerMemo.memo());
                        });
        course.update(member, courseUpdateRequest.title(), courseUpdateRequest.content(), newImgUrl);
        return null;
    }

    /**
     * 새로운 코스를 생성합니다.
     *
     * @param memberId
     * @param courseCreateRequest 코스 생성 요청 정보
     * @return 생성된 코스의 ID
     * @throws IOException 이미지 파일 처리 중 예외 발생
     */
    @Transactional
    public Long createCourse(Long memberId, CourseCreateRequest courseCreateRequest) throws IOException {
        return courseRepository.save(Course.createCourse(memberService.getMember(memberId),
                imageService.uploadFile(courseCreateRequest.image()),
                courseCreateRequest,
                courseCreateRequest.placePerMemos().stream()
                        .map(placePerMemo -> CoursePlace.create(placeService.getPlace(getPlaceId(placePerMemo)), placePerMemo.memo()))
                        .toList())
        ).getId();
    }

    /**
     * 오늘의 추천 코스를 조회합니다.
     *
     * @return 오늘의 추천 코스 리스트
     */
    public List<CourseResponse> getTodayPick() {
        return courseRepository.findRandomCourses().stream()
                .map(CourseResponse::of)
                .toList();
    }

    /**
     * 특정 코스를 조회합니다.
     *
     * @param courseId 코스 ID
     * @return 특정 코스의 상세 정보
     * @throws CourseNotFoundException 코스를 찾을 수 없는 경우 예외 발생
     */
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);
    }

    private Long getPlaceId(PlacePerMemo placePerMemo) {
        return placePerMemo.placeId();
    }

    private CoursePlace getCoursePlace(Long courseId, Long placeId) {
        return coursePlaceRepository.findByCourseIdAndPlaceId(courseId, placeId).orElseThrow(CoursePlaceNotFoundException::new);
    }

    private Long getMemberId(Course course) {
        return course.getMember().getId();
    }

    private String getNickName(Course course) {
        return course.getMember().getNickname();
    }
}
