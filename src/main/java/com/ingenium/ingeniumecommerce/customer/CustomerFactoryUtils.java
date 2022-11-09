package com.ingenium.ingeniumecommerce.customer;

public class CustomerFactoryUtils {
    private CustomerFactoryUtils() {
    }

    public static Customer createCustomer(final CustomerDTO customerDTO) {
        return Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
    }
}
