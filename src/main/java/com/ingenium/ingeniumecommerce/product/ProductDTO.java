package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private Money price;
}
