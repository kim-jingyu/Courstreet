package com.hyundairoad.hyundairoad.member.service;

import com.hyundairoad.hyundairoad.member.domain.dto.MemberDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.SignupDTO;
import com.hyundairoad.hyundairoad.member.domain.vo.Member;
import com.hyundairoad.hyundairoad.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public MemberDTO getMember(Long memberId) {
        return memberMapper.getMemberById(memberId);
    }

    @Transactional(readOnly = true)
    public Member getLoginMemberByEmail(String email) {
        return memberMapper.getMemberByEmail(email);
    }

    public void signup(SignupDTO signupDTO) {
        if (memberMapper.getMemberByLoginId(signupDTO.getLoginId()) != null) return;
        signupDTO.setPassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        memberMapper.signup(signupDTO);
    }

    public void like() {

    }

    @Transactional(readOnly = true)
    public void getMemberLiked(Long memberId) {

    }
}
