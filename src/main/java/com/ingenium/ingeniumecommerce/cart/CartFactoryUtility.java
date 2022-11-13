package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.productEntry.ProductEntry;

import java.util.Collections;

public final class CartFactoryUtility {
    private CartFactoryUtility() {

    }

    public static Cart createCart(final Product product, final int quantity) {
        final ProductEntry productEntry = new ProductEntry(product, quantity);
        final Money money = productEntry.calculateEntryPrice();
        return Cart.builder().
                productEntries(Collections.singleton(productEntry))
                .totalPrice(money)
                .build();
    }
}