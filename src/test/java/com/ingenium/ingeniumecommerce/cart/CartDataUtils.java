package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntry;

import java.util.Set;

public class CartDataUtils {
    public static Cart createCart(final Long cartId, final Set<CartEntry> cartEntries) {
        return Cart.builder()
                .id(cartId)
                .cartEntries(cartEntries)
                .build();
    }
}
