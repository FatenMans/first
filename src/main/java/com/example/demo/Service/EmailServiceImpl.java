package com.example.demo.Service;

import com.example.demo.Entite.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class  EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDetails.getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMsgBody());

            javaMailSender.send(message);

            return "Email sent successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email: " + e.getMessage();
        }
    }


    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }
}
