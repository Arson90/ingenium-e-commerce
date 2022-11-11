package com.ingenium.ingeniumecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCommandRepository extends JpaRepository<Order, Long> {
}
