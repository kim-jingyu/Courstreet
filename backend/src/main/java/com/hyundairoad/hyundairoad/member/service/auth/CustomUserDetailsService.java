package com.hyundairoad.hyundairoad.member.service.auth;

import com.hyundairoad.hyundairoad.member.domain.vo.Member;
import com.hyundairoad.hyundairoad.member.domain.vo.auth.CustomUserDetails;
import com.hyundairoad.hyundairoad.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.getMemberByEmail(username);
        if (member == null) return null;
        return new CustomUserDetails(member);
    }
}
