package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.user.User;

public class OrderFactoryUtils {
    private OrderFactoryUtils() {}

    public static Order createOrder(final User user) {
        return Order.builder()
                .user(user)
                .build();
    }
}
