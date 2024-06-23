package hyundairoad.hyundairoad.mail.service;

import hyundairoad.hyundairoad.global.config.EmailConfig;
import hyundairoad.hyundairoad.global.utils.RedisUtil;
import hyundairoad.hyundairoad.mail.exception.InvalidMimeMailException;
import hyundairoad.hyundairoad.mail.exception.InvalidSimpleMailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSendService {
    public static final String TITLE = "비밀번호 찾기 인증 이메일입니다.";
    private final JavaMailSender mailSender;
    private final EmailConfig emailConfig;
    private final RedisUtil redisUtil;

    // 메일 보내는 방식이 2가지 존재
    //1. SimpleMailMessage 를 통한 간단한 텍스트 전송 방법
    //2. MimeMessage 를 통한 html 반영 메일 전송 방법
    public void sendSimpleMailMessage(String email) {
        simpleMailSend(email);
    }

    public void sendMimeMessage(String email) {
        mimeMailSend(emailConfig.getUsername(), email, TITLE, getContent(makeRandomNumber()));
    }

    public boolean checkAuthNumber(String email, String authNum) {
        if (redisUtil.getData(authNum) == null) return false;
        if (redisUtil.getData(authNum).equals(email)) return true;
        return false;
    }

    private void simpleMailSend(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setTo(email); // 메일 수신자
            simpleMailMessage.setSubject(TITLE); // 메일 제목
            simpleMailMessage.setText(getSimpleContent(makeRandomNumber())); // 메일 내용
            mailSender.send(simpleMailMessage);
            log.info("비밀번호 찾기 메일 발송 성공 - 간편");
        } catch (Exception e) {
            log.info("비밀번호 찾기 메일 발송 실패 - 간편");
            throw new InvalidSimpleMailException();
        }
    }

    private void mimeMailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("비밀번호 찾기 메일 발송 성공 - MIME");
        } catch (MessagingException e) {
            log.info("비밀번호 찾기 메일 발송 실패 - MIME");
            throw new InvalidMimeMailException();
        }
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
