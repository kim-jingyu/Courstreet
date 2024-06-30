package com.hyundairoad.mail.service;

import com.hyundairoad.mail.domain.Mail;
import com.hyundairoad.mail.exception.MailSendException;
import com.hyundairoad.mail.exception.MailTryCountException;
import com.hyundairoad.mail.exception.MailVerificationException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.hyundairoad.global.constants.MailConstants.PASSWORD_FIND_CONTENT_POST;
import static com.hyundairoad.global.constants.MailConstants.PASSWORD_FIND_CONTENT_PREV;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 메일 서비스
 *
 * 작성자: 김진규
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;

    /**
     * 메일을 전송합니다.
     *
     * @param mail 메일 정보
     * @param code 인증 코드
     */
    public void sendMail(Mail mail, int code) {
        long count = getEmailRequestCount(mail.getEmail());
        if (count == 5) throw new MailTryCountException();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(mail.getEmail());
            helper.setSubject(mail.getTitle());
            helper.setText(mail.getContent(), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSendException();
        }

        saveVerificationCode(mail.getEmail(), String.valueOf(code));
        increaseEmailRequestCount(mail.getEmail());
    }

    /**
     * 저장된 인증 코드를 가져옵니다.
     *
     * @param email 이메일 주소
     * @return 인증 코드
     */
    public String getVerificationCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    /**
     * 인증 코드가 포함된 메일 내용을 생성합니다.
     *
     * @param authNumber 인증 코드
     * @return 메일 내용
     */
    public String getContent(int authNumber) {
        return PASSWORD_FIND_CONTENT_PREV + authNumber + PASSWORD_FIND_CONTENT_POST;
    }

    /**
     * 랜덤 인증 코드를 생성합니다.
     *
     * @return 랜덤 인증 코드
     */
    public int makeRandomNumber() {
        return new Random().nextInt(899999) + 100000;
    }

    /**
     * 이메일 인증을 처리합니다.
     *
     * @param code      입력된 인증 코드
     * @param savedCode 저장된 인증 코드
     */
    public void verifyEmail(String code, String savedCode) {
        if (!code.equals(savedCode)) throw new MailVerificationException();
    }

    /**
     * 이메일 요청 횟수를 가져옵니다.
     *
     * @param email 이메일 주소
     * @return 요청 횟수
     */
    private long getEmailRequestCount(String email) {
        String key = "email_request_count:" + email;
        String value = redisTemplate.opsForValue().get(key);
        return value != null ? Long.parseLong(value) : 0;
    }

    /**
     * 이메일 요청 횟수를 증가시킵니다.
     *
     * @param email 이메일 주소
     */
    private void increaseEmailRequestCount(String email) {
        String key = "email_request_count:" + email;
        Long count = redisTemplate.opsForValue().increment(key);
        if (count == 5) redisTemplate.expire(key, 24, HOURS);
    }

    /**
     * 인증 코드를 저장합니다.
     *
     * @param email 이메일 주소
     * @param code  인증 코드
     */
    private void saveVerificationCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, 3, MINUTES);
    }
}
