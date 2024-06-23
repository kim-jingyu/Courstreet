package hyundairoad.hyundairoad.member.controller;

import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.auth.dto.JoinRequest;
import hyundairoad.hyundairoad.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OauthLoginController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();

        Member loginMember = memberService.getMemberByEmail(email);

        if (loginMember != null) {
            response.put("name", loginMember.getName());
            response.put("role", role);
        }

        response.put("loginType", "oauth-login");
        response.put("pageName", "oauth 로그인");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> join(@Valid @RequestBody JoinRequest joinRequest,
                                                    BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        response.put("loginType", "oauth-login");
        response.put("pageName", "oauth 로그인");

        if (memberService.getMemberByEmail(joinRequest.getEmail()) != null) {
            bindingResult.addError(new FieldError("joinRequest", "email", "ID가 존재합니다."));
        }

        if (bindingResult.hasErrors()) {
            response.put("errors", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(response);
        }

        memberService.securityJoin(joinRequest);
        response.put("message", "회원가입이 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/info")
//    public ResponseEntity<Map<String, Object>> memberInfo(Authentication authentication) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("loginType", "oauth-login");
//        response.put("pageName", "oauth 로그인");
//
//        Member loginMember = memberService.getLoginMemberByLoginId(authentication.getName());
//        response.put("member", loginMember);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/admin")
//    public ResponseEntity<Map<String, Object>> adminPage() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("loginType", "oauth-login");
//        response.put("pageName", "oauth 로그인");
//        return ResponseEntity.ok(response);
//    }
}
