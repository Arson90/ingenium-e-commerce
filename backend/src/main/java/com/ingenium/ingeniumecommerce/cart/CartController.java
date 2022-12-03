package com.ingenium.ingeniumecommerce.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ingenium")
class CartController {
    private final CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts/{cartId}")
    ResponseEntity<CartView> getCartById(@PathVariable final Long cartId) {
        final CartView cartView = this.cartService.findCartById(cartId);
        return ResponseEntity.ok(cartView);
    }

    @GetMapping("/carts")
    ResponseEntity<List<CartView>> getCarts() {
        final List<CartView> cartList = this.cartService.findAllCarts();
        if (!cartList.isEmpty()){
            return ResponseEntity.ok(cartList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/carts/{productId}/quantity/{quantity}")
    ResponseEntity<CartResponseDTO> addProductToCart(@PathVariable final Long productId, @PathVariable final int quantity,
                                              @CookieValue(value = "cartId", required = false) final String cartCookieId,
                                              final HttpServletResponse response) {
        final CartResponseDTO cartResponseDTO = this.cartService.addProductToCart(productId, quantity, cartCookieId, response);
        return ResponseEntity.ok().body(cartResponseDTO);
    }

    @DeleteMapping("/carts/products/{productId}")
    ResponseEntity<Void> deleteProductFromCart(@CookieValue(value = "cartId", required = false) final String cartCookie,
                                               @PathVariable final Long productId) {
        cartService.deleteProductFromCart(productId, cartCookie);
        return ResponseEntity.ok().build();
    }
}