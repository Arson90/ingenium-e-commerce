package com.ingenium.ingeniumecommerce.orderNotification;

import com.ingenium.ingeniumecommerce.util.mail.EmailDetails;
import com.ingenium.ingeniumecommerce.util.mail.EmailDetailsFactoryUtils;
import com.ingenium.ingeniumecommerce.util.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class OrderNotificationConfig {
    private final OrderNotificationService orderNotificationService;
    private final EmailService emailService;
    @Scheduled(cron = "*/30 * * * * *")
    public void sentPlacedOrderNotification() {
        log.info("cronjob sentPlacedOrderNotification is starting now ...");
        final List<OrderNotification> placedOrderNotifications = this.orderNotificationService.getPlacedOrderNotifications();
        placedOrderNotifications.forEach(notification -> notification.setLock(true));

        for (OrderNotification orderNotification : placedOrderNotifications) {
            orderNotification.addSendingAttempts();
            if (orderNotification.getSendingAttempts() <= 5) {
                final EmailDetails emailDetails = EmailDetailsFactoryUtils.convertOrderNotificationToSimpleMail(orderNotification);
                try {
                    this.emailService.sendSimpleMail(emailDetails);
                    orderNotification.setLock(false);
                    orderNotification.setStatus(OrderNotificationStatus.SENT);
                    this.orderNotificationService.saveOrderNotification(orderNotification);
                } catch (MailException ex) {
                    log.error("Mail couldn't send. Order id " + orderNotification.getOrderId());
                    if (!orderNotification.getStatus().equals(OrderNotificationStatus.FAILED)) {
                        orderNotification.setStatus(OrderNotificationStatus.FAILED);
                        this.orderNotificationService.saveOrderNotification(orderNotification);
                    }
                }
            } else {
                orderNotification.setSendingAttempts(0);
                orderNotification.setLock(false);
                orderNotification.setStatus(OrderNotificationStatus.FAILED);
                this.orderNotificationService.saveOrderNotification(orderNotification);
            }
        }
        log.info("cronjob sentPlacedOrderNotification has already done !!!");
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public void sentFailedOrderNotification() {
        final List<OrderNotification> failedOrderNotifications = this.orderNotificationService.getFailedOrderNotifications();
        for (OrderNotification orderNotification : failedOrderNotifications) {
            try {
                final EmailDetails emailDetails = EmailDetailsFactoryUtils.convertOrderNotificationToSimpleMail(orderNotification);
                this.emailService.sendSimpleMail(emailDetails);
                orderNotification.setStatus(OrderNotificationStatus.SENT);
                this.orderNotificationService.saveOrderNotification(orderNotification);
            } catch (MailException ex) {
                log.error("Mail couldn't send. Order id " + orderNotification.getOrderId());
                if (!orderNotification.getStatus().equals(OrderNotificationStatus.FAILED)) {
                    orderNotification.setStatus(OrderNotificationStatus.CANCELED);
                    this.orderNotificationService.saveOrderNotification(orderNotification);
                }
            }
        }
    }
}
