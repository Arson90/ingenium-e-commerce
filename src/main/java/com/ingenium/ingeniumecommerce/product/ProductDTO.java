package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import lombok.Getter;

@Getter
public class ProductDTO {
    private String productName;
    private Money price;
}
