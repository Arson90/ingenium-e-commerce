package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.Customer;

public class UserFactoryUtils {
    private UserFactoryUtils() {}

    public static User createUser(final UserRequestDTO userRequestDTO, final String encodedPassword, final Customer customer) {
        return User.builder()
                .username(userRequestDTO.getUsername())
                .password(encodedPassword)
                .customer(customer)
                .role("USER")
                .build();
    }
}