package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.productEntry.ProductEntry;

import java.util.Set;

public class OrderFactoryUtils {
    private OrderFactoryUtils() {}

    public static Order createOrder(final Customer customer, final Set<ProductEntry> productEntries, final PaymentType paymentType) {
        return Order.builder()
                .customer(customer)
                .productEntries(productEntries)
                .paymentType(paymentType)
                .build();
    }
}
