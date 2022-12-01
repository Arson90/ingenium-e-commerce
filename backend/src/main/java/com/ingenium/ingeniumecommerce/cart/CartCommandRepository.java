package com.ingenium.ingeniumecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCommandRepository extends JpaRepository<Cart, Long> {

}