package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderEntryData {
    private ProductData productData;
    private int quantity;
}
