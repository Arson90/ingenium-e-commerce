package com.ingenium.ingeniumecommerce.util.mail;

import com.ingenium.ingeniumecommerce.orderNotification.OrderNotification;

public final class EmailDetailsFactoryUtils {
    private EmailDetailsFactoryUtils() {
    }

    public static EmailDetails convertOrderNotificationToSimpleMail(final OrderNotification orderNotification) {
        return EmailDetails.builder()
                .recipient(orderNotification.getEmail())
                .subject("Your order has been already placed")
                .msgBody("Hello, your order " + orderNotification.getOrderId() + " is being processed. You will get order confirmation in next message.")
                .build();
    }
}
