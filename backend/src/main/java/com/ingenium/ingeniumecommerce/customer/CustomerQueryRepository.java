package com.ingenium.ingeniumecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerQueryRepository extends JpaRepository<Customer, Long> {
    Optional<CustomerView> findCustomerById(final Long customerId);
}
