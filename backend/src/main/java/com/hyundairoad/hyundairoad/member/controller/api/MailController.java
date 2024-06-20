package com.hyundairoad.hyundairoad.member.controller.api;

import com.hyundairoad.hyundairoad.member.domain.dto.EmailCheckDTO;
import com.hyundairoad.hyundairoad.member.domain.dto.EmailRequestDto;
import com.hyundairoad.hyundairoad.member.exception.InvalidEmailException;
import com.hyundairoad.hyundairoad.member.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hyundairoad.hyundairoad.constants.ErrorMsg.INVALID_EMAIL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailSendService mailSendService;

    @PostMapping("/mailSend")
    public ResponseEntity<Void> mailSend(@Validated EmailRequestDto emailRequestDto) {
        log.info("이메일 인증 이메일: {}", emailRequestDto.getEmail());
        mailSendService.sendMimeMessage(emailRequestDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mailAuthCheck")
    public ResponseEntity<Void> mailAuthCheck(@Validated EmailCheckDTO emailCheckDTO) {
        boolean checkedAuthNumber = mailSendService.checkAuthNumber(emailCheckDTO.getEmail(), emailCheckDTO.getAuthNum());
        if (!checkedAuthNumber) {
            throw new InvalidEmailException(INVALID_EMAIL);
        }
        return ResponseEntity.ok().build();
    }
}
