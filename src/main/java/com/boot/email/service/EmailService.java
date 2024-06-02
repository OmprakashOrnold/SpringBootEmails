package com.boot.email.service;

public interface EmailService {

    void sendSimpleMailMessage(String name, String to,String token);
    void sendMimlMessageWithAtachments(String name, String to,String token);
    void sendMimeMessageWithEmbeddedImages(String name, String to,String token);
    void sendMimeMessageWithEmbeddedIFiles(String name, String to,String token);
    void sendHtmlEmail(String name, String to,String token);
    void sendHtmlEmailWithEmbeddedIFiles(String name, String to,String token);

}
