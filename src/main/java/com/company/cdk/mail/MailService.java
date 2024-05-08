package com.company.cdk.mail;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(String to, String subject, String body, String attachmentPath) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
    	
    	MimeMessage message = mailSender.createMimeMessage();
    	try {
            MimeMessageHelper msg = new MimeMessageHelper(message, true);
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            msg.addAttachment("Resume.pdf", file);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
