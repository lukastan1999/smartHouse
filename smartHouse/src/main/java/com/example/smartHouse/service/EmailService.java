package com.example.smartHouse.service;

import com.example.smartHouse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("sveisvasta2024@outlook.com");
        mailSender.send(message);
    }

    public void sendActivationEmail(User user) {
        String activationLink = "http://localhost:8080/api/users/activate?token=" + user.getActivationToken();
        String emailBody = "Hi " + user.getName() + ",\n\n" +
                "Thank you for registering. Please click the following link to activate your account:\n" +
                activationLink + "\n\n" +
                "This link will expire in 24 hours.\n\n" +
                "Regards,\nSmartHouse Team";

        sendEmail(user.getEmail(), "Account Activation", emailBody);
    }
}
