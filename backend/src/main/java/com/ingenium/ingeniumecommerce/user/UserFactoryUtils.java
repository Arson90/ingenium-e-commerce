package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.security.auth.RegistrationRequest;

public class UserFactoryUtils {
    private UserFactoryUtils() {}

    public static User createUser(final RegistrationRequest registrationRequest, final String encodedPassword, final Customer customer) {
        return User.builder()
                .username(registrationRequest.getUsername())
                .password(encodedPassword)
                .customer(customer)
                .role("ROLE_USER")
                .build();
    }
}