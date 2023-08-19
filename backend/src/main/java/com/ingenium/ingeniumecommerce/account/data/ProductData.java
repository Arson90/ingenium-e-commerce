package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductData {
    private String productName;
    private BigDecimal price;
}
