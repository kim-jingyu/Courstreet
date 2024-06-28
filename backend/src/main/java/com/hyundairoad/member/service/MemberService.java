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
 * 작성일: 2024-06-29
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    @Transactional
    public String saveProfileImage(Long memberId, MultipartFile multipartFile) throws IOException {
        Member member = getMember(memberId);
        member.changeProfileImg(imageService.updateFile(member.getImageUrl(), multipartFile));
        memberRepository.save(member);
        return member.getImageUrl();
    }

    public String getProfileImage(Long memberId) {
        return getMember(memberId).getImageUrl();
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }
}
