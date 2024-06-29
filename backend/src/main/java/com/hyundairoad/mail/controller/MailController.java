package com.hyundairoad.mail.controller;

import com.hyundairoad.mail.domain.Mail;
import com.hyundairoad.mail.domain.dto.MailCheckRequest;
import com.hyundairoad.mail.domain.dto.MailRequest;
import com.hyundairoad.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hyundairoad.global.constants.MailConstants.PASSWORD_FIND_TITLE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<Void> mailSend(@RequestBody @Validated MailRequest request) {
        int code = mailService.makeRandomNumber();
        String content = mailService.getContent(code);
        mailService.sendMail(Mail.builder()
                .email(request.getEmail())
                .title(PASSWORD_FIND_TITLE)
                .content(content)
                .build(), code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> mailVerify(@RequestBody @Validated MailCheckRequest request) {
        String email = request.getEmail();
        String code = request.getCode();
        String savedCode = mailService.getVerificationCode(email);
        mailService.verifyEmail(code, savedCode);
        return ResponseEntity.noContent().build();
    }
}
