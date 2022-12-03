package com.ingenium.ingeniumecommerce.product;

import java.util.List;

public interface ProductService {
    ProductView findProductByName(final String productName);

    ProductView findProductById(final Long productId);

    List<ProductView> findAllProducts();

    ProductResponseDTO createProduct(final ProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(final ProductRequestDTO productRequestDTO, final Long productId);

    void deleteProduct(final Long productId);
}