package com.ingenium.ingeniumecommerce.orderNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderNotificationCommandRepository extends JpaRepository<OrderNotification, Long> {
}
