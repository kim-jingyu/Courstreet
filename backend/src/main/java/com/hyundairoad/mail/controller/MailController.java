package com.hyundairoad.mail.controller;

import com.hyundairoad.mail.domain.dto.EmailCheckRequest;
import com.hyundairoad.mail.domain.dto.EmailRequest;
import com.hyundairoad.mail.exception.InvalidEmailException;
import com.hyundairoad.mail.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailSendService mailSendService;

    @PostMapping("/mailSend")
    public ResponseEntity<Void> mailSend(@Validated EmailRequest request) {
        log.info("이메일 인증 이메일: {}", request.getEmail());
        mailSendService.sendMimeMessage(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mailAuthCheck")
    public ResponseEntity<Void> mailAuthCheck(@Validated EmailCheckRequest request) {
        boolean checkedAuthNumber = mailSendService.checkAuthNumber(request.getEmail(), request.getAuthNum());
        if (!checkedAuthNumber) {
            throw new InvalidEmailException();
        }
        return ResponseEntity.ok().build();
    }
}
