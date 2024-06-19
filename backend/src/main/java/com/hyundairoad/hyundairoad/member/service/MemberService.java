package com.hyundairoad.hyundairoad.member.service;

import com.hyundairoad.hyundairoad.member.domain.dto.MemberDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.SignupDTO;
import com.hyundairoad.hyundairoad.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public MemberDTO getMember(Long memberId) {
        return memberMapper.getMemberById(memberId);
    }

    public void login() {

    }

    public void signup(SignupDTO signupDTO) {
        memberMapper.signup(signupDTO);

    }

    @Transactional(readOnly = true)
    public void getMemberLiked(Long memberId) {

    }
}
