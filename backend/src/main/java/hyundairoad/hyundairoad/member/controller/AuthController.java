package hyundairoad.hyundairoad.member.controller;

import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.auth.dto.LoginRequest;
import hyundairoad.hyundairoad.member.exception.MemberNotFoundException;
import hyundairoad.hyundairoad.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private MemberRepository userRepository;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {

        userRepository.save(Member.builder()
                .email(request.email())
                .password(request.password())
                .build());
        return ResponseEntity.ok().body("User registered successfully");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        log.info("request:{}", request);
        Member foundUser = userRepository.findByEmail(request.email()).orElseThrow(MemberNotFoundException::new);
        log.info("member={}", foundUser.toString());

        log.info("pw={}", request.password());
        // JWT 토큰 생성 및 설정 (여기서는 생략)
        String token = "generated-jwt-token"; // 실제로는 JWT 라이브러리로 생성
        response.setHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok().body("Login successful");
    }
}

