package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cookie.CookieService;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductNotFoundException;
import com.ingenium.ingeniumecommerce.product.ProductQueryRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    private final CartCommandRepository cartCommandRepository;
    private final CartQueryRepository cartQueryRepository;
    private final ProductQueryRepository productQueryRepository;
    private final CookieService cookieService;

    public CartServiceImpl(CartCommandRepository cartCommandRepository, CartQueryRepository cartQueryRepository, ProductQueryRepository productQueryRepository, CookieService cookieService) {
        this.cartCommandRepository = cartCommandRepository;
        this.cartQueryRepository = cartQueryRepository;
        this.productQueryRepository = productQueryRepository;
        this.cookieService = cookieService;
    }

    @Override
    public CartView findCartById(final Long cartId) {
        return this.cartQueryRepository.findCartById(cartId)
                .orElseThrow(() ->CartNotFoundException.createForCartId(cartId));
    }

    @Override
    public List<CartView> findAllCarts() {
        return this.cartQueryRepository.findAllBy();
    }

    @Override
    public CartView addProductToCart(final Long productId, final int quantity, final String cartCookieId, final HttpServletResponse response) {
        final Product product = productQueryRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.createForProductId(productId));

        if (cartCookieId == null) {
            return createNewCartAndAddProduct(product, quantity, response);
        }
        return addProductToExistingCart(product, quantity, cartCookieId);
    }

    private CartView createNewCartAndAddProduct(final Product product, final int quantity, final HttpServletResponse response) {
        final Cart createdCart = CartFactoryUtility.createCart(product, quantity);
        final Cart savedCart = cartCommandRepository.save(createdCart);
        cookieService.createCookieForCart(savedCart.getId(), response);
        return savedCart.toCartView();
    }
    private CartView addProductToExistingCart(final Product product, final int quantity, final String cartCookieId) {
        final Long cartId = Long.valueOf(cartCookieId);
        return cartCommandRepository.findById(cartId)
                .map(cart -> cart.addProduct(product, quantity).toCartView())
                .orElseThrow(() -> CartNotFoundException.createForCartId(cartId));
    }
}