package hyundairoad.hyundairoad.member.service.auth;

import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.auth.CustomUserDetails;
import hyundairoad.hyundairoad.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElse(null);
        return new CustomUserDetails(member);
    }
}
