package com.ingenium.ingeniumecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCommandRepository extends JpaRepository<Customer, Long> {
}
