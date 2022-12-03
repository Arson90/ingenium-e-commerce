package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.product.ProductResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartEntryResponseDTO {
    private Long id;
    private ProductResponseDTO productResponseDTO;
    private int quantity;
}
