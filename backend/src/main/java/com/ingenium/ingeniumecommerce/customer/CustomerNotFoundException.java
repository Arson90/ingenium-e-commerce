package com.ingenium.ingeniumecommerce.customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(final String message) {
        super(message);
    }

    public static CustomerNotFoundException createForCartId(final Long customerId) {
        return new CustomerNotFoundException(String.format("Customer not found, ID: %d", customerId));
    }
}