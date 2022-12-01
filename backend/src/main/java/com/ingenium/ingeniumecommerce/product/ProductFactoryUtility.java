package com.ingenium.ingeniumecommerce.product;

public final class ProductFactoryUtility {
    private ProductFactoryUtility() {
    }

    public static Product createProduct(final ProductDTO productDTO) {
        return Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .build();
    }
}
