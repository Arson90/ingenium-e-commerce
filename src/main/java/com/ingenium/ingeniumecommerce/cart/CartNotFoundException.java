package com.ingenium.ingeniumecommerce.cart;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(final String message) {
        super(message);
    }

    public static CartNotFoundException createForCartId(final Long cartId) {
        return new CartNotFoundException(String.format("Cart not found, ID: %d", cartId));
    }
}