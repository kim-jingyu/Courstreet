package com.hyundairoad.member.controller;

import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.auth.member.MemberOnly;
import com.hyundairoad.member.domain.dto.MemberResponse;
import com.hyundairoad.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 회원 컨트롤러
 *
 * 작성자: 김진규
 */
@Tag(name = "회원", description = "회원 API입니다.")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 프로필 이미지를 수정합니다.
     *
     * @param id             회원 ID
     * @param multipartFile  프로필 이미지 파일
     * @return 수정된 프로필 이미지 URL
     * @throws IOException 이미지 저장 중 오류 발생 시
     */
    @Operation(summary = "회원 프로필 이미지를 수정합니다.", description = "회원 프로필 이미지를 수정하는 API입니다.")
    @PostMapping(value = "/{id}/profile")
    public ResponseEntity<String> updateProfileImage(@PathVariable Long id, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(memberService.saveProfileImage(id, multipartFile));
    }

    /**
     * 회원 프로필 이미지를 조회합니다.
     *
     * @param id 회원 ID
     * @return 프로필 이미지 URL
     */
    @Operation(summary = "회원 프로필 이미지를 조회합니다.", description = "회원 프로필 이미지를 조회하는 API입니다.")
    @GetMapping("/{id}/profile")
    public ResponseEntity<String> getProfileImage(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getProfileImage(id));
    }

    /**
     * 회원 정보를 조회합니다.
     *
     * @param id 회원 ID
     * @return 회원 정보 응답
     */
    @Operation(summary = "회원 정보를 조회합니다.", description = "회원 정보를 조회하는 API입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(MemberResponse.from(memberService.getMember(id)));
    }

    /**
     * 회원을 비활성화합니다.
     *
     * @param accessor 인증된 회원 정보
     * @return 처리 결과
     */
    @Operation(summary = "회원을 비활성화합니다.", description = "회원을 비활성화하는 API입니다.")
    @MemberOnly
    @PatchMapping
    public ResponseEntity<Void> deactivateMember(@MemberCheck Accessor accessor) {
        memberService.deactivateMember(accessor.getMemberId());
        return ResponseEntity.noContent().build();
    }

    /**
     * 회원을 탈퇴합니다.
     *
     * @param accessor 인증된 회원 정보
     * @return 처리 결과
     */
    @Operation(summary = "회원을 탈퇴합니다.", description = "회원을 탈퇴하는 API입니다.")
    @MemberOnly
    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@MemberCheck Accessor accessor) {
        memberService.deleteMember(accessor.getMemberId());
        return ResponseEntity.noContent().build();
    }
}
