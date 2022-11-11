package com.ingenium.ingeniumecommerce.product;

import java.util.List;

public interface ProductService {
    ProductView findProductByName(final String productName);

    ProductView findProductById(final Long productId);

    List<ProductView> findAllProducts();

    ProductView createProduct(final ProductDTO productDTO);

    ProductView updateProduct(final ProductDTO productDTO, final Long productId);

    void deleteProduct(final Long productId);
}