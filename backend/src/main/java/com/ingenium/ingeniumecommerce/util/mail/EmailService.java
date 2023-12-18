package com.ingenium.ingeniumecommerce.util.mail;

public interface EmailService {
    void sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
