package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CartView {
    private Long id;
    private Set<CartEntryView> cartEntryView;
}