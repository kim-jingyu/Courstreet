package com.hyundairoad.hyundairoad.member.controller.api;

import com.hyundairoad.hyundairoad.member.domain.dto.LoginDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.SignupDTO;
import com.hyundairoad.hyundairoad.member.exception.InvalidSignupException;
import com.hyundairoad.hyundairoad.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hyundairoad.hyundairoad.constants.ErrorMsg.INVALID_SIGNUP;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/")
    public String getMember() {
        return "member";
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(@Validated LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Validated SignupDTO signupDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidSignupException(INVALID_SIGNUP);
        }

        memberService.signup(signupDTO);
        return ResponseEntity.ok().build();
    }
}
