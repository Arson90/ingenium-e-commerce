package com.ingenium.ingeniumecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderQueryRepository extends JpaRepository<Order, Long> {
    Optional<OrderView> findOrderById(final Long orderId);

    List<OrderView> findAllBy();

    List<Order> findAllByCustomerId(final Long orderId);
}