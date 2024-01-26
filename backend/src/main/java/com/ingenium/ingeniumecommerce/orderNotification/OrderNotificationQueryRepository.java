package com.ingenium.ingeniumecommerce.orderNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderNotificationQueryRepository extends JpaRepository<OrderNotification, Long> {
    @Query("SELECT o FROM OrderNotification o WHERE ( o.status = 'CREATED' AND o.isLock IS FALSE ) OR ( o.status = 'FAILED' AND o.isLock IS TRUE )")
    List<OrderNotification> findAllCreatedAndFailedNotification();

    @Query("SELECT o FROM OrderNotification o WHERE o.status = 'FAILED' AND o.isLock IS FALSE")
    List<OrderNotification> findAllFailedNotification();
}
