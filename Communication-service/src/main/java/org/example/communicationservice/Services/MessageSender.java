package org.example.communicationservice.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    @Value("${spring.mail.username}")
    private String sender;


    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        helper.setFrom(sender);

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(sender);

            System.out.println("Sending email to: " + to);
            System.out.println("Subject: " + subject);
            System.out.println("HTML content: " + content);

            javaMailSender.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw e;
        }


        javaMailSender.send(message);
    }
}
