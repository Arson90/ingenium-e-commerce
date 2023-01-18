package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class OrderRequestWrapper {
    private Customer customer;
    private Map<Product, Integer> cartEntries;
}
