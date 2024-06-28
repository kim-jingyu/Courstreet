package com.hyundairoad.mail.controller;

import com.hyundairoad.mail.domain.dto.EmailCheckRequest;
import com.hyundairoad.mail.domain.dto.EmailRequest;
import com.hyundairoad.mail.exception.InvalidEmailException;
import com.hyundairoad.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final EmailService emailService;

    @PostMapping("/mailSend")
    public ResponseEntity<Void> mailSend(@Validated EmailRequest request) {
//        emailService.sendMimeMessage(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mailAuthCheck")
    public ResponseEntity<Void> mailAuthCheck(@Validated EmailCheckRequest request) {
        boolean checkedAuthNumber = emailService.checkAuthNumber(request.getEmail(), request.getAuthNum());
        if (!checkedAuthNumber) {
            throw new InvalidEmailException();
        }
        return ResponseEntity.ok().build();
    }
}
