package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntry;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryDataUtils;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductDataUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class CartTest {

    @Test
    public void shouldProductAddToCart() {
        //given
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(new BigDecimal("10.00")));
        final Cart cart = CartDataUtils.createCart(1L, new HashSet<>());
        final CartEntry cartEntry = CartEntryDataUtils.createCartEntry(1L, product, cart, 1);
        cart.getCartEntries().add(cartEntry);
        final int sizeBeforeAddingProduct = cart.getCartEntries().size();

        final Product newProduct = ProductDataUtils.createProduct(2L, "newProduct", new Money(new BigDecimal("165.00")));

        //when
        cart.addProduct(newProduct, 1);
        final int sizeAfterAddingProduct = cart.getCartEntries().size();

        //then
        assertEquals(1, sizeBeforeAddingProduct);
        assertEquals(2, sizeAfterAddingProduct);
    }

    @Test
    public void shouldIncreaseQuantityOfExistingProduct() {
        //given
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(new BigDecimal("10.00")));
        final Cart cart = CartDataUtils.createCart(1L, new HashSet<>());
        final CartEntry cartEntry = CartEntryDataUtils.createCartEntry(1L, product, cart, 1);
        cart.getCartEntries().add(cartEntry);

        final int quantityBeforeIncreasing = cart.getCartEntries().iterator().next().getQuantity();

        //when
        cart.addProduct(product, 2);
        final int quantityAfterIncreasing = cart.getCartEntries().iterator().next().getQuantity();

        //then
        assertEquals(1, quantityBeforeIncreasing);
        assertEquals(3, quantityAfterIncreasing);
    }

    @Test
    public void shouldRemoveProductById() {
        //given
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(new BigDecimal("10.00")));
        final Cart cart = CartDataUtils.createCart(1L, new HashSet<>());
        final CartEntry cartEntry = CartEntryDataUtils.createCartEntry(1L, product, cart, 1);
        cart.getCartEntries().add(cartEntry);
        final int sizeBeforeRemoveProduct = cart.getCartEntries().size();

        //when
        cart.deleteProductById(product.getId());
        final int sizeAfterRemoveProduct = cart.getCartEntries().size();

        //then
        assertEquals(1, sizeBeforeRemoveProduct);
        assertEquals(0, sizeAfterRemoveProduct);
    }
}