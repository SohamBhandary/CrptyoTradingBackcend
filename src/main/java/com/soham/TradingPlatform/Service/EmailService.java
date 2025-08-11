package com.soham.TradingPlatform.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static java.awt.SystemColor.text;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    public  void sendVerficationOtpEmail(String email,String otp) throws MessagingException {
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,"utf-8");

        String subject="Verify OTP";

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(String.valueOf(text));
        mimeMessageHelper.setTo(email);

        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException(e.getMessage());
        }


    }
}
