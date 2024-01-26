package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.orderNotification.OrderNotification;
import com.ingenium.ingeniumecommerce.orderNotification.OrderNotificationStatus;

import java.time.LocalDate;

public final class OrderFactoryUtils {
    private OrderFactoryUtils() {
    }

    public static OrderResponseDTO convertOrderToOrderResponseDto(final Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .email(order.getCustomer().getEmail())
                .build();
    }

    public static OrderNotification convertOrderToOrderNotification(final Order order) {
        return OrderNotification.builder()
                .date(LocalDate.now())
                .orderId(order.getId())
                .email(order.getCustomer().getEmail())
                .status(OrderNotificationStatus.CREATED)
                .isLock(false)
                .sendingAttempts(0)
                .build();
    }
}
