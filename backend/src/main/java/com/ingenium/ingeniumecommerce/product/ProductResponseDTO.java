package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResponseDTO {
    private Long id;
    private String productName;
    private Money price;
}
