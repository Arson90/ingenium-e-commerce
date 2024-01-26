package com.ingenium.ingeniumecommerce.util.mail;

import org.springframework.mail.MailException;

public interface EmailService {
    void sendSimpleMail(EmailDetails details) throws MailException;
    void sendMailWithAttachment(EmailDetails details);
}
