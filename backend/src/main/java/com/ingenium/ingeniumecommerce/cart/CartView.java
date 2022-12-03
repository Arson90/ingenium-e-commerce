package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntryView;

import java.util.Set;

public interface CartView {
    Long getId();
    Set<CartEntryView> getCartEntries();
}