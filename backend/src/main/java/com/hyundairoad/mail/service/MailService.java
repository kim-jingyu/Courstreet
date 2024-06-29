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

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;

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

    public String getVerificationCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public String getContent(int authNumber) {
        return PASSWORD_FIND_CONTENT_PREV + authNumber + PASSWORD_FIND_CONTENT_POST;
    }

    public int makeRandomNumber() {
        return new Random().nextInt(899999) + 100000;
    }

    public void verifyEmail(String code, String savedCode) {
        if (!code.equals(savedCode)) throw new MailVerificationException();
    }

    private long getEmailRequestCount(String email) {
        String key = "email_request_count:" + email;
        String value = redisTemplate.opsForValue().get(key);
        return value != null ? Long.parseLong(value) : 0;
    }

    private void increaseEmailRequestCount(String email) {
        String key = "email_request_count:" + email;
        Long count = redisTemplate.opsForValue().increment(key);
        if (count == 5) redisTemplate.expire(key, 24, HOURS);
    }

    private void saveVerificationCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, 3, MINUTES);
    }
}
