package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.cart.Cart;
import com.ingenium.ingeniumecommerce.product.Product;

public class CartEntryDataUtils {
    public static CartEntry createCartEntry(final Long cartEntryId, final Product product, final Cart cart, final int quantity) {
        return CartEntry.builder()
                .id(cartEntryId)
                .product(product)
                .cart(cart)
                .quantity(quantity)
                .build();
    }
}
