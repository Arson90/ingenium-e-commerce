package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntryFactoryUtils;

public final class CartFactoryUtils {
    private CartFactoryUtils() {
    }

    public static CartResponseDTO convertCartToCartResponseDto(final Cart cart) {
        return CartResponseDTO.builder()
                .id(Long.valueOf(cart.getId()))
                .cartEntriesResponseDTO(CartEntryFactoryUtils.
                        convertCartEntriesToCartEntriesResponseDTO(cart.getCartEntries()))
                .build();
    }
}
