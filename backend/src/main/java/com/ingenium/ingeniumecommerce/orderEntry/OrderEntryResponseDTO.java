package com.ingenium.ingeniumecommerce.orderEntry;

import com.ingenium.ingeniumecommerce.product.ProductResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderEntryResponseDTO {
    private Long id;
    private ProductResponseDTO productResponseDTO;
    private int quantity;
}
