package com.hyundairoad.mypage.service;

import com.hyundairoad.course.service.CourseService;
import com.hyundairoad.mypage.domain.dto.MyPageCourseResponse;
import com.hyundairoad.mypage.domain.dto.MyPagePlaceResponse;
import com.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final CourseService courseService;
    private final PlaceService placeService;

    public List<MyPageCourseResponse> getMyPageCourseList(Long memberId) throws MalformedURLException {
        return courseService.getCoursesWithMember(memberId).stream()
                .map(MyPageCourseResponse::of)
                .toList();
    }

    public List<MyPageCourseResponse> getMyPageLikeCourseList(Long memberId) throws MalformedURLException {
        return courseService.getLikedCoursesWithMember(memberId).stream()
                .map(MyPageCourseResponse::ofLikeCourse)
                .toList();
    }

    public List<MyPagePlaceResponse> getMyPageLikePlaceList(Long memberId) throws MalformedURLException {
        return placeService.getLikedPlacesByMemberId(memberId).stream()
                .map(MyPagePlaceResponse::of)
                .collect(Collectors.toList());
    }
}
