package com.hyundairoad.hyundairoad.member.mapper;

import com.hyundairoad.hyundairoad.member.domain.dto.FindPwDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.LoginDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.MemberDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.SignupDTO;
import com.hyundairoad.hyundairoad.member.domain.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member getMemberByLoginId(String loginId);
    Member getMemberByEmail(String email);
    MemberDTO getMemberById(@Param("memberId") Long memberId);
    void signup(SignupDTO signupDTO);
    void save(Member member);
    MemberDTO login(LoginDTO loginDTO);
    MemberDTO getMemberWithFindPW(FindPwDTO findPwDTO);
}
