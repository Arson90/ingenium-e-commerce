package com.ingenium.ingeniumecommerce.orderEntry;

import com.ingenium.ingeniumecommerce.product.ProductView;

public interface OrderEntryView {
    Long getId();
    ProductView getProduct();
    int getQuantity();
}