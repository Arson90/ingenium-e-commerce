package com.ingenium.ingeniumecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommandRepository extends JpaRepository<Product, Long> {

}