package com.ingenium.ingeniumecommerce.orderNotification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderNotificationServiceImpl implements OrderNotificationService{
    private final OrderNotificationQueryRepository orderNotificationQueryRepository;
    private final OrderNotificationCommandRepository orderNotificationCommandRepository;
    @Override
    public List<OrderNotification> getPlacedOrderNotifications() {
        return this.orderNotificationQueryRepository.findAllCreatedAndFailedNotification();
    }

    @Override
    public void saveOrderNotification(final OrderNotification orderNotification) {
        this.orderNotificationCommandRepository.save(orderNotification);
    }

    @Override
    public List<OrderNotification> getFailedOrderNotifications() {
        return this.orderNotificationQueryRepository.findAllFailedNotification();
    }
}
