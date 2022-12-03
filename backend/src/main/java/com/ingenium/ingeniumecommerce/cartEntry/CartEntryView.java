package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.product.ProductView;

public interface CartEntryView {
    Long getId();
    ProductView getProduct();
    int getQuantity();
}