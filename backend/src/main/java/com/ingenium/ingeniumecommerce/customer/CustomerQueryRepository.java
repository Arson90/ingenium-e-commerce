package com.ingenium.ingeniumecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerQueryRepository extends JpaRepository<Customer, Long> {
}
