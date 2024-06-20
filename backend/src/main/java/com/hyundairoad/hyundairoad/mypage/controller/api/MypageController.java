package com.hyundairoad.hyundairoad.mypage.controller.api;

import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPageCourseResponseDTO;
import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPageMyCourseDTO;
import com.hyundairoad.hyundairoad.mypage.domain.dto.MyPagePlaceResponseDTO;
import com.hyundairoad.hyundairoad.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {
    private MyPageService myPageService;

    @GetMapping("/mycourse")
    public ResponseEntity<List<MyPageCourseResponseDTO>> getMyPageMyCourse(MyPageMyCourseDTO myPageMyCourseDTO) {
        return ResponseEntity.ok()
                .body(myPageService.getMyPageCourseList(myPageMyCourseDTO));
    }

    @GetMapping("/mylike/mycourse")
    public ResponseEntity<List<MyPageCourseResponseDTO>> getMyPageLikeMyCourse(Long memberId) {
        return ResponseEntity.ok()
                .body(myPageService.getMyPageLikeCourseList(memberId));
    }

    @GetMapping("/mylike/myplace")
    public ResponseEntity<List<MyPagePlaceResponseDTO>> getMyPageLikeMyPlace(Long memberId) {
        return ResponseEntity.ok()
                .body(myPageService.getMyPageLikePlaceList(memberId));
    }

    @GetMapping("/mycomments")
    public ResponseEntity<List<MyPageCourseResponseDTO>> getMyPageMyComments(Long memberId) {
        return ResponseEntity.ok()
                .body(myPageService.getMyPageCourseListWithComments(memberId));
    }
}
