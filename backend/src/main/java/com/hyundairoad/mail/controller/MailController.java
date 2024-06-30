package com.hyundairoad.mail.controller;

import com.hyundairoad.mail.domain.Mail;
import com.hyundairoad.mail.domain.dto.MailCheckRequest;
import com.hyundairoad.mail.domain.dto.MailRequest;
import com.hyundairoad.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hyundairoad.global.constants.MailConstants.PASSWORD_FIND_TITLE;

/**
 * 메일 컨트롤러
 *
 * 작성자: 김진규
 */
@Tag(name = "메일", description = "메일 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    /**
     * 메일 전송 요청을 처리합니다.
     *
     * @param request 메일 요청 정보
     * @return 처리 결과
     */
    @Operation(summary = "메일 전송 요청을 처리합니다.", description = "이메일로 인증 코드를 전송하는 API입니다.")
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

    /**
     * 메일 인증 요청을 처리합니다.
     *
     * @param request 메일 인증 요청 정보
     * @return 처리 결과
     */
    @Operation(summary = "메일 인증 요청을 처리합니다.", description = "전송된 인증 코드를 확인하여 이메일을 인증하는 API입니다.")
    @PostMapping("/verify")
    public ResponseEntity<Void> mailVerify(@RequestBody @Validated MailCheckRequest request) {
        String email = request.getEmail();
        String code = request.getCode();
        String savedCode = mailService.getVerificationCode(email);
        mailService.verifyEmail(code, savedCode);
        return ResponseEntity.noContent().build();
    }
}
