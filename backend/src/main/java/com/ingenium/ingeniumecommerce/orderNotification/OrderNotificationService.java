package com.ingenium.ingeniumecommerce.orderNotification;

import java.util.List;

public interface OrderNotificationService {
    List<OrderNotification> getPlacedOrderNotifications();

    void saveOrderNotification(final OrderNotification orderNotification);

    List<OrderNotification> getFailedOrderNotifications();
}
