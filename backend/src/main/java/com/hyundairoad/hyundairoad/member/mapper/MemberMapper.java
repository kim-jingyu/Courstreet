package com.hyundairoad.hyundairoad.member.mapper;

import com.hyundairoad.hyundairoad.member.domain.dto.FindPwDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.LoginDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.MemberDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDTO getMemberById(@Param("memberId") Long memberId);
    void signup(SignupDTO signupDTO);
    MemberDTO login(LoginDTO loginDTO);
    MemberDTO getMemberWithFindPW(FindPwDTO findPwDTO);
}
