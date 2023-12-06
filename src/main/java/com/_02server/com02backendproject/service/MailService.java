package com._02server.com02backendproject.service;

import com._02server.com02backendproject.global.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com._02server.com02backendproject.global.BaseResponseStatus.UNABLE_TO_SEND_EMAIL;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;

    public void sendEmail(String toEmail,
                          String title,
                          String authCode) throws BaseException {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, authCode);

        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new BaseException(UNABLE_TO_SEND_EMAIL);
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
