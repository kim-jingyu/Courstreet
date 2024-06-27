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
import com.hyundairoad.image.service.ImageService;
import com.hyundairoad.member.domain.Member;
import com.hyundairoad.member.domain.like.MemberCourseLike;
import com.hyundairoad.member.repository.MemberCourseLikeRepository;
import com.hyundairoad.member.service.MemberService;
import com.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<CourseResponse> getAllCourses() throws MalformedURLException {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            addToResponseList(course, courseResponseList);
        }
        return courseResponseList;
    }

    public CourseResponse getCourseDetail(Long id) throws MalformedURLException {
        Course course = getCourse(id);
        Temp temp = getResult(course);
        if (checkLiked(course, temp.memberId())) CourseResponse.of(course, temp.nickName(), temp.memberId(), temp.courseImage(), false);
        return CourseResponse.of(course, temp.nickName(), temp.memberId(), temp.courseImage(), true);
    }

    @Transactional
    public Void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return null;
    }

    public List<CourseResponse> searchCourse(String keyword) throws MalformedURLException {
        List<CourseResponse> list = new ArrayList<>();
        for (Course course : courseRepository.findByTitleOrContentContaining(keyword)) {
            Temp temp = getResult(course);
            list.add(CourseResponse.of(course, temp.nickName, temp.memberId, temp.courseImage, false));
        }
        return list;
    }

    @Transactional
    public Void updateCourse(Long id, CourseUpdateRequest courseUpdateRequest) throws IOException {
        Course course = getCourse(id);
        String newImgUrl = imageService.updateFile(course.getCourseImgUrl(), courseUpdateRequest.image());
        Member member = memberService.getMember(courseUpdateRequest.memberId());
        List<PlacePerMemo> placePerMemos = courseUpdateRequest.placePerMemos();
        for (PlacePerMemo placePerMemo : placePerMemos) {
            Long placeId = getPlaceId(placePerMemo);
            getCoursePlace(placeId).update(placeService.getPlace(placeId), placePerMemo.memo());
        }
        course.update(member, courseUpdateRequest.title(), courseUpdateRequest.content(), newImgUrl);
        return null;
    }

    @Transactional
    public Void createCourse(CourseCreateRequest courseCreateRequest) throws IOException {
        courseRepository.save(Course.createCourse(memberService.getMember(
                courseCreateRequest.memberId()),
                imageService.uploadFile(courseCreateRequest.image()),
                courseCreateRequest,
                courseCreateRequest.placePerMemos().stream()
                .map(placePerMemo -> CoursePlace.create(placeService.getPlace(getPlaceId(placePerMemo)), placePerMemo.memo()))
                .toList()));
        return null;
    }

    public List<CourseResponse> getTodayPick() throws MalformedURLException {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            addToResponseList(getCourse((long) (random.nextInt((int) getCount()) + 1)), courseResponseList);
        }
        return null;
    }

    public List<CourseResponse> getCoursesWithMember(Long memberId) throws MalformedURLException {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courseRepository.findByMemberId(memberId)) {
            addToResponseList(course, courseResponseList);
        }
        return courseResponseList;
    }

    public List<CourseResponse> getLikedCoursesWithMember(Long memberId) throws MalformedURLException {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (MemberCourseLike memberCourseLike : memberCourseLikeRepository.findByMemberId(memberId)) {
            addToResponseList(memberCourseLike.getCourse(), courseResponseList);
        }
        return courseResponseList;
    }

    private Long getPlaceId(PlacePerMemo placePerMemo) {
        return placePerMemo.placeId();
    }

    private CoursePlace getCoursePlace(Long placeId) {
        return coursePlaceRepository.findById(placeId).orElseThrow(CoursePlaceNotFoundException::new);
    }

    private Temp getResult(Course course) throws MalformedURLException {
        String nickName = getNickName(course);
        Long memberId = getMemberId(course);
        Resource courseImage = getCourseImage(course);
        return new Temp(nickName, memberId, courseImage);
    }

    private long getCount() {
        return courseRepository.count();
    }

    private Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    private void addToResponseList(Course course, List<CourseResponse> courseResponseList) throws MalformedURLException {
        Temp temp = getTemp(course);
        if (checkLiked(course, temp.memberId())) {
            courseResponseList.add(CourseResponse.of(course, temp.nickName(), temp.memberId(), temp.courseImage(), false));
            return;
        }
        courseResponseList.add(CourseResponse.of(course, temp.nickName(), temp.memberId(), temp.courseImage(), true));
    }

    private Temp getTemp(Course course) throws MalformedURLException {
        return new Temp(getNickName(course), getMemberId(course), getCourseImage(course));
    }

    private record Temp(String nickName, Long memberId, Resource courseImage) {
    }

    private boolean checkLiked(Course course, Long memberId) {
        MemberCourseLike memberCourseLike = memberCourseLikeRepository.findByMemberIdAndCourseId(memberId, course.getId());
        return memberCourseLike == null || memberCourseLike.getCount() == 0;
    }

    private Resource getCourseImage(Course course) throws MalformedURLException {
        return imageService.getImage(course.getCourseImgUrl());
    }

    private Long getMemberId(Course course) {
        return course.getMember().getId();
    }

    private String getNickName(Course course) {
        return course.getMember().getNickname();
    }
}
