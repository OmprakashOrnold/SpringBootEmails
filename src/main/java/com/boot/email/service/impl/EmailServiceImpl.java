package com.boot.email.service.impl;

import com.boot.email.service.EmailService;
import com.boot.email.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystem;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String UTF_8_ENCODING = "UTF-8";

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromMail;

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject( NEW_USER_ACCOUNT_VERIFICATION );
            simpleMailMessage.setFrom( fromMail );
            simpleMailMessage.setTo( to );
            simpleMailMessage.setText( EmailUtils.getEmailMessage( name, host, token ) );
            mailSender.send( simpleMailMessage );


        } catch (Exception exception) {
            log.error( "sendSimpleMailMessage method execution fail {} ", exception.getMessage() );
            throw new RuntimeException( exception.getMessage() );
        }

    }

    @Override
    @Async
    public void sendMimlMessageWithAtachments(String name, String to, String token) {
        try {

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message, true, UTF_8_ENCODING );
            helper.setPriority( 1 );
            helper.setSubject( NEW_USER_ACCOUNT_VERIFICATION );
            helper.setFrom( fromMail );
            helper.setTo( to );
            helper.setText( EmailUtils.getEmailMessage( name, host, token ) );

            FileSystemResource resource = new FileSystemResource( new File( "D://interview notes pdf//Common Step - Java Most Asked Coding Questions and Answers.pdf" ));
            helper.addAttachment( resource.getFilename(), resource );
            mailSender.send( message );

        } catch (Exception exception) {
            log.error( "sendSimpleMailMessage method execution fail {} ", exception.getMessage() );
            throw new RuntimeException( exception.getMessage() );
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedImages(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendMimeMessageWithEmbeddedIFiles(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedIFiles(String name, String to, String token) {

    }

    private MimeMessage getMimeMessage() {
        return mailSender.createMimeMessage();
    }

}
