package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductCommandRepository productCommandRepository;

    @Mock
    private ProductQueryRepository productQueryRepository;

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductNotFound() {
        //when
        when(productQueryRepository.findById(anyLong())).thenThrow(ProductNotFoundException.class);

        //then
        productServiceImpl.findProductById(anyLong());
    }

    @Test
    public void shouldExecuteOneTime() {
        //given
        final ProductDTO productDTO = new ProductDTO("testProduct", new Money(BigDecimal.valueOf(100)));
        when(productCommandRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        //when
        productServiceImpl.createProduct(productDTO);

        //then
        verify(productCommandRepository).save(any());
    }

    @Test
    public void shouldUpdateProduct() {
        //given
        final Product product = ProductDataUtils.createProduct(1L, "testProduct", new Money(BigDecimal.valueOf(150.00).setScale(2, RoundingMode.UNNECESSARY)));
        final String productName = product.getProductName();
        final BigDecimal productPrice = product.getPrice().getPrice();
        final ProductDTO productDTO = new ProductDTO("productDTO", new Money(BigDecimal.valueOf(10.00).setScale(2, RoundingMode.UNNECESSARY)));

        //when
        when(productCommandRepository.findById(anyLong())).thenReturn(Optional.of(product));

        //then
        final ProductView productView = productServiceImpl.updateProduct(productDTO, 1L);
        final String viewProductName = productView.getProductName();
        final BigDecimal viewProductPrice = productView.getPrice().getPrice();

        assertNotEquals(productName, viewProductName);
        assertNotEquals(productPrice, viewProductPrice);
    }
}