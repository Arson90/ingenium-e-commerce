package com.ingenium.ingeniumecommerce.util.mail;

import com.ingenium.ingeniumecommerce.util.pdf.PDFExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    private final PDFExportService pdfExportService;

    @Override
    public void sendSimpleMail(final EmailDetails details) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@ingenium.com");
        message.setTo(details.getRecipient());
        message.setSubject(details.getSubject());
        message.setText(details.getMsgBody());
        javaMailSender.send(message);
    }

    @Override
    public String sendMailWithAttachment(final EmailDetails details) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@ingenium.com");
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody());
            ByteArrayInputStream byteArrayInputStream = pdfExportService.exportPDF(4L);
            helper.addAttachment("Order confirmation", new ByteArrayDataSource(byteArrayInputStream, "application/pdf"));
            javaMailSender.send(message);
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
