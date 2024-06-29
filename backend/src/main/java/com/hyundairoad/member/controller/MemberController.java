package com.hyundairoad.member.controller;

import com.hyundairoad.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "회원", description = "회원 API입니다.")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 프로필 이미지를 수정합니다.", description = "회원 프로필 이미지를 수정하는 API입니다.")
    @PostMapping(value = "/{id}/profile")
    public ResponseEntity<String> updateProfileImage(@PathVariable Long id, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(memberService.saveProfileImage(id, multipartFile));
    }

    @Operation(summary = "회원 프로필 이미지를 조회합니다.", description = "회원 프로필 이미지를 조회하는 API입니다.")
    @GetMapping("/{id}/profile")
    public ResponseEntity<String> getProfileImage(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getProfileImage(id));
    }
}
