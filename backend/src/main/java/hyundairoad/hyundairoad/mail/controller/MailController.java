package hyundairoad.hyundairoad.mail.controller;

import hyundairoad.hyundairoad.mail.domain.dto.EmailCheckRequest;
import hyundairoad.hyundairoad.mail.domain.dto.EmailRequest;
import hyundairoad.hyundairoad.mail.exception.InvalidEmailException;
import hyundairoad.hyundairoad.mail.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
