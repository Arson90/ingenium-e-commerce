package com.ingenium.ingeniumecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerQueryRepository extends JpaRepository<Customer, Long> {
}
