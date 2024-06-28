package com.hyundairoad.mail.service;

import com.hyundairoad.global.utils.RedisUtil;
import com.hyundairoad.mail.domain.EmailMessage;
import com.hyundairoad.member.service.MemberService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    public static final String TITLE = "비밀번호 찾기 인증 이메일입니다.";
    private final JavaMailSender mailSender;
    private final MemberService memberService;
    private final RedisUtil redisUtil;

    public String sendMail(EmailMessage emailMessage, String type) {
        String code = createCode();
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        memberService.setTempPassword(emailMessage.getReceiver(), code);

        if (type.equals("password")) {
            return null;
        }

        return null;
    }

    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97));
                case 1 -> key.append((char) (random.nextInt(26) + 65));
                default -> key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    public boolean checkAuthNumber(String email, String authNum) {
        if (redisUtil.getData(authNum) == null) return false;
        if (redisUtil.getData(authNum).equals(email)) return true;
        return false;
    }

    private String getSimpleContent(String authNumber) {
        return "인증 번호는 " + authNumber + "입니다.";
    }

    private String getContent(String authNumber) {
        return "Hyundai-Road에 방문해주셔서 감사합니다." +
                "<br><br>" +
                "인증 번호는 " + authNumber + "입니다.";
    }

    private String makeRandomNumber() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }
}
