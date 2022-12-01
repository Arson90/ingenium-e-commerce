package com.ingenium.ingeniumecommerce.customer;

public interface CustomerService {
    CustomerView findCustomerById(final Long customerId);
}