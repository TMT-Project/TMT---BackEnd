package com.tmtbackend.service;

import com.tmtbackend.model.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final String fromEmail = "mnsh.pv1@gmail.com";

    private final String password = "gkkl nwda qjzk drvy";

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public String generateOTP() {
        int otp = 1000 + SECURE_RANDOM.nextInt(9999);
        return String.valueOf(otp);
    }

    public void verifyEmail(String email,String otp) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Tip My Ticket - Email Verification");
            message.setText("Your OTP for email verification is :" + otp);
            Transport.send(message);
            log.info("OTP email sent successfully...");
        } catch (MessagingException mex) {
            log.warn(mex.toString());
        }

    }
}
