package com.hyundairoad.member.controller;

import com.hyundairoad.like.domain.dto.LikeCourseRequest;
import com.hyundairoad.like.domain.dto.LikePlaceRequest;
import com.hyundairoad.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/{id}/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(memberService.saveProfileImage(id, multipartFile));
    }

    @GetMapping("/{id}/profileImage")
    public ResponseEntity<Resource> getProfileImage(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok().body(memberService.getProfileImage(id));
    }

    @PostMapping("/like/course")
    public ResponseEntity<Void> likeCourse(@RequestBody LikeCourseRequest likeCourseRequest) {
        return ResponseEntity.ok().body(memberService.likeCourse(likeCourseRequest));
    }

    @PostMapping("/like/place")
    public ResponseEntity<Void> likePlace(@RequestBody LikePlaceRequest likePlaceRequest) {
        return ResponseEntity.ok().body(memberService.likePlace(likePlaceRequest));
    }

    @PostMapping("/cancel/like/course")
    public ResponseEntity<Void> cancelLikeCourse(@RequestBody LikeCourseRequest likeCourseRequest) {
        return ResponseEntity.ok().body(memberService.cancelLikeCourse(likeCourseRequest));
    }

    @PostMapping("/cancel/like/place")
    public ResponseEntity<Void> cancelLikePlace(@RequestBody LikePlaceRequest likePlaceRequest) {
        return ResponseEntity.ok().body(memberService.cancelLikePlace(likePlaceRequest));
    }
}
