package com.hyundairoad.hyundairoad.mypage.service;

import com.hyundairoad.hyundairoad.course.service.CourseService;
import com.hyundairoad.hyundairoad.image.domain.dto.ImageResponseDTO;
import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import com.hyundairoad.hyundairoad.image.service.ImageService;
import com.hyundairoad.hyundairoad.member.service.MemberService;
import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPageCourseResponseDTO;
import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPageMyCourseDTO;
import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPagePlaceResponseDTO;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberService memberService;
    private final CourseService courseService;
    private final ImageService imageService;
    private final PlaceService placeService;

    public List<MyPageCourseResponseDTO> getMyPageCourseList(MyPageMyCourseDTO myPageMyCourseDTO) {
        return courseService.getCoursesWithMember(memberService.getMember(myPageMyCourseDTO.getMemberId()).getMemberId()).stream()
                .map(MyPageCourseResponseDTO::of)
                .collect(Collectors.toList());
    }

    public List<MyPageCourseResponseDTO> getMyPageLikeCourseList(Long memberId) {
        return courseService.getLikedCoursesWithMember(memberService.getMember(memberId).getMemberId()).stream()
                .map(likedCourseResponseDTO -> MyPageCourseResponseDTO.ofLikeCourse(likedCourseResponseDTO, imageService.getImageListWithCoursePlace(likedCourseResponseDTO.getCourseId())))
                .collect(Collectors.toList());
    }

    public List<MyPagePlaceResponseDTO> getMyPageLikePlaceList(Long memberId) {
        return placeService.getLikedPlacesByMemberId(memberId).stream()
                .map(likedPlaceDTO -> {
                    List<Image> imageListWithMember = imageService.getImageListWithMember(memberId);
                    return MyPagePlaceResponseDTO.of(likedPlaceDTO, imageListWithMember.stream()
                            .map(ImageResponseDTO::of)
                            .collect(Collectors.toList()));
                })
                .collect(Collectors.toList());
    }

    public List<MyPageCourseResponseDTO> getMyPageCourseListWithComments(Long memberId) {
        return courseService.getCoursesWithComments(memberId).stream()
                .map(MyPageCourseResponseDTO::ofCommentsCourse)
                .collect(Collectors.toList());
    }
}
