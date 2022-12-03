package com.ingenium.ingeniumecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartQueryRepository extends JpaRepository<Cart, Long> {
    Optional<CartView> findCartById(final Long cartID);

    List<CartView> findAllBy();
}