package com.ingenium.ingeniumecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductQueryRepository extends JpaRepository<Product, Long> {
    Optional<ProductView> findByProductName(final String productName);

    Optional<ProductView> findProductById(final Long productId);

    List<ProductView> findAllBy();
}