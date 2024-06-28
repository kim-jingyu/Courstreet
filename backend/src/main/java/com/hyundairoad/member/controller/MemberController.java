package com.hyundairoad.member.controller;

import com.hyundairoad.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MemberController
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PatchMapping(value = "/{id}/profile")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(memberService.saveProfileImage(id, multipartFile));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<String> getProfileImage(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getProfileImage(id));
    }
}
