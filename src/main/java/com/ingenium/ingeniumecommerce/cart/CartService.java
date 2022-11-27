package com.ingenium.ingeniumecommerce.cart;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    CartView findCartById(final Long cartId);
    List<CartView> findAllCarts();
    CartView addProductToCart(final Long productID, final int quantity, final String cartCookieId, final HttpServletResponse response);
    boolean deleteProductFromCart(final Long productId, final String cartCookieId);
}