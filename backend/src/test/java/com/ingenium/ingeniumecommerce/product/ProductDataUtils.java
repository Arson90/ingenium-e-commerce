package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;

public class ProductDataUtils {

    public static Product createProduct(final Long productId, final String productName, final Money price) {
        return Product.builder()
                .id(productId)
                .productName(productName)
                .price(price)
                .build();
    }
}
