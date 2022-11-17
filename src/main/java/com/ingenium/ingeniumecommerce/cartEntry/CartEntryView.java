package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.product.ProductView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CartEntryView {
    private ProductView productView;
    private int quantity;
}