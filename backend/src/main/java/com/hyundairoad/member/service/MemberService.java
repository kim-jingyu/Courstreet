package com.hyundairoad.member.service;

import com.hyundairoad.image.service.ImageService;
import com.hyundairoad.member.domain.Member;
import com.hyundairoad.member.exception.MemberNotFoundException;
import com.hyundairoad.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * MemberService
 *
 * 작성자: 김진규
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    /**
     * 회원 프로필 이미지를 저장합니다.
     *
     * @param memberId       회원 ID
     * @param multipartFile  프로필 이미지 파일
     * @return 저장된 프로필 이미지 URL
     * @throws IOException 이미지 저장 중 오류 발생 시
     */
    @Transactional
    public String saveProfileImage(Long memberId, MultipartFile multipartFile) throws IOException {
        Member member = getMember(memberId);
        member.changeProfileImg(imageService.updateFile(member.getImageUrl(), multipartFile));
        memberRepository.save(member);
        return member.getImageUrl();
    }

    /**
     * 회원 프로필 이미지를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 프로필 이미지 URL
     */
    public String getProfileImage(Long memberId) {
        return getMember(memberId).getImageUrl();
    }

    /**
     * 회원 정보를 조회합니다.
     *
     * @param memberId 회원 ID
     * @return 회원 정보
     * @throws MemberNotFoundException 회원을 찾을 수 없는 경우 발생
     */
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    /**
     * 회원을 비활성화합니다.
     *
     * @param memberId 회원 ID
     */
    @Transactional
    public void deactivateMember(Long memberId) {
        Member member = getMember(memberId);
        member.changeStatus();
    }

    /**
     * 회원을 삭제합니다.
     *
     * @param memberId 회원 ID
     */
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = getMember(memberId);
        memberRepository.delete(member);
    }
}
