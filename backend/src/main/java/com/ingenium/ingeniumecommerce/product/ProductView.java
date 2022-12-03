package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;

public interface ProductView {
    Long getId();
    String getProductName();
    Money getPrice();
}