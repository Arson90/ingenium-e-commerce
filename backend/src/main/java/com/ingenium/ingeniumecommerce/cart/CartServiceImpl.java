package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cookie.CookieService;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductNotFoundException;
import com.ingenium.ingeniumecommerce.product.ProductQueryRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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
                .orElseThrow(() -> CartNotFoundException.createForCartId(cartId));
    }

    @Override
    public List<CartView> findAllCarts() {
        return this.cartQueryRepository.findAllBy();
    }

    @Override
    @Transactional
    public CartResponseDTO addProductToCart(final Long productId, final int quantity, final String cartCookieId, final HttpServletResponse response) {
        final Product product = productQueryRepository.findById(productId)
                .orElseThrow(() -> ProductNotFoundException.createForProductId(productId));

        if (cartCookieId == null) {
            final Cart savedCart = createCartAndAddProduct(product, quantity);
            cookieService.createCookieForCart(savedCart.getId(), response);
            return CartFactoryUtils.convertCartToCartResponseDto(savedCart);
        }
        return addProduct(product, quantity, cartCookieId);
    }

    @Override
    @Transactional
    public boolean deleteProductFromCart(final Long productId, final String cartCookieId) {
        if (cartCookieId != null) {
            final Long cartId = Long.valueOf(cartCookieId);
            return this.cartCommandRepository.findById(cartId)
                    .map(cart -> cart.deleteProductById(productId))
                    .orElseThrow(() -> CartNotFoundException.createForCartId(productId));
        }
        return false;
    }

    private Cart createCartAndAddProduct(final Product product, final int quantity) {
        final Cart cart = new Cart();
        cart.addProduct(product, quantity);
        return cartCommandRepository.save(cart);
    }

    private CartResponseDTO addProduct(final Product product, final int quantity, final String cartCookieId) {
        final Long cartId = Long.valueOf(cartCookieId);
        return cartCommandRepository.findById(cartId)
                .map(cart -> cart.addProduct(product, quantity))
                .map(CartFactoryUtils::convertCartToCartResponseDto)
                .orElseThrow(() -> CartNotFoundException.createForCartId(cartId));
    }
}