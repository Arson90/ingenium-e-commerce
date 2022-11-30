package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntry;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryDataUtils;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryView;
import com.ingenium.ingeniumecommerce.cookie.CookieService;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductDataUtils;
import com.ingenium.ingeniumecommerce.product.ProductQueryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {
    @Mock
    private CartCommandRepository cartCommandRepository;
    @Mock
    private CartQueryRepository cartQueryRepository;
    @Mock
    private ProductQueryRepository productQueryRepository;
    @Mock
    private CookieService cookieService;
    @InjectMocks
    private CartServiceImpl cartServiceImpl;

    @Test(expected = CartNotFoundException.class)
    public void shouldThrowExceptionWhenCartNotFound() {
        //given
        when(cartQueryRepository.findCartById(1L)).thenThrow(CartNotFoundException.class);

        //when
        cartServiceImpl.findCartById(1L);
    }

    @Test
    public void shouldCreateCartAndAddProduct() {
        //given
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(new BigDecimal("10.00")));
        when(productQueryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));
        doNothing().when(cookieService).createCookieForCart(anyString(), any(HttpServletResponse.class));
        when(cartCommandRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        //when
        final CartView cartView = cartServiceImpl.addProductToCart(1L, 1, null, any(HttpServletResponse.class));
        final CartEntryView cartEntryView = cartView.getCartEntryView().iterator().next();

        //then
        Assert.assertEquals(1, cartView.getCartEntryView().size());
        Assert.assertEquals(1, cartEntryView.getQuantity());
        Assert.assertEquals(Long.valueOf(1), cartEntryView.getProductView().getId());
        Assert.assertEquals(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY), cartEntryView.getProductView().getPrice().getPrice());
    }

    @Test
    public void shouldRemoveProductFromCart() {
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(new BigDecimal("10.00")));
        final Cart cart = CartDataUtils.createCart(1L, new HashSet<>());
        final CartEntry cartEntry = CartEntryDataUtils.createCartEntry(1L, product, cart, 1);
        cart.getCartEntries().add(cartEntry);
        final int sizeBeforeRemoveProduct = cart.getCartEntries().size();

        when(cartCommandRepository.findById(anyLong())).thenReturn(Optional.of(cart));

        //when
        final boolean isProductRemoved = cartServiceImpl.deleteProductFromCart(product.getId(), cart.getId());
        final int sizeAfterRemoveProduct = cart.getCartEntries().size();

        //then
        Assert.assertTrue(isProductRemoved);
        Assert.assertEquals(1, sizeBeforeRemoveProduct);
        Assert.assertEquals(0, sizeAfterRemoveProduct);
    }
}