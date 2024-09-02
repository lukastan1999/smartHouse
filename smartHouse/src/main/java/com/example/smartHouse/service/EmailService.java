package com.example.smartHouse.service;

import com.example.smartHouse.entity.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private final SendGrid sendGrid;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public EmailService() {
        this.sendGrid = new SendGrid(sendGridApiKey);
    }

    public void sendEmail(String to, String subject, String text) {
        Email from = new Email("no-reply@smarthouse.com"); // Replace with your "from" email
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", text);

        Mail mail = new Mail(from, subject, toEmail, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle the exception properly in production
        }
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
