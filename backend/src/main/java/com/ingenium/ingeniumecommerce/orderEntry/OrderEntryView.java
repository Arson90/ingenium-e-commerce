package com.ingenium.ingeniumecommerce.orderEntry;

import com.ingenium.ingeniumecommerce.product.ProductView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderEntryView {
    private ProductView productView;
    private int quantity;
}