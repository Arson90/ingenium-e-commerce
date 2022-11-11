package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;

public class UserFactoryUtils {
    private UserFactoryUtils() {}

    public static User createAnonymousUser(final Customer customer, final Address address) {
        return User.builder()
                .name("anonymous")
                .customer(customer)
                .address(address)
                .build();
    }
}
