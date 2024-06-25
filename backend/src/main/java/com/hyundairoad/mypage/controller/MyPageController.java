package com.hyundairoad.mypage.controller;

import com.hyundairoad.mypage.domain.dto.MyPageCourseResponse;
import com.hyundairoad.mypage.domain.dto.MyPagePlaceResponse;
import com.hyundairoad.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{id}")
    public ResponseEntity<List<MyPageCourseResponse>> getMyPageMyCourse(@PathVariable Long id) throws MalformedURLException {
        return ResponseEntity.ok().body(myPageService.getMyPageCourseList(id));
    }

    @GetMapping("/mylike/mycourse/{id}")
    public ResponseEntity<List<MyPageCourseResponse>> getMyPageLikeMyCourse(@PathVariable Long id) throws MalformedURLException {
        return ResponseEntity.ok().body(myPageService.getMyPageLikeCourseList(id));
    }

    @GetMapping("/mylike/myplace/{id}")
    public ResponseEntity<List<MyPagePlaceResponse>> getMyPageLikeMyPlace(@PathVariable Long id) throws MalformedURLException {
        return ResponseEntity.ok().body(myPageService.getMyPageLikePlaceList(id));
    }
}

