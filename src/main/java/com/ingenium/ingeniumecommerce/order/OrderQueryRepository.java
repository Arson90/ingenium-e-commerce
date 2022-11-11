package com.ingenium.ingeniumecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderQueryRepository extends JpaRepository<Order, Long> {
    Optional<OrderView> findOrderById(final Long orderId);

    List<OrderView> findAllBy();
}