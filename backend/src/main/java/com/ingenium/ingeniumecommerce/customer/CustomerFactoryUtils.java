package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.account.data.CustomerData;
import com.ingenium.ingeniumecommerce.address.Address;

public class CustomerFactoryUtils {
    private CustomerFactoryUtils() {
    }

    public static Customer convertCustomerRequestDtoToCustomer(final CustomerRequestDTO customerRequestDTO, final Address address) {
        return Customer.builder()
                .firstName(customerRequestDTO.getFirstName())
                .lastName(customerRequestDTO.getLastName())
                .email(customerRequestDTO.getEmail())
                .phoneNumber(customerRequestDTO.getPhoneNumber())
                .address(address)
                .build();
    }

    public static CustomerData convertCustomerToCustomerData(final Customer customer) {
        return CustomerData.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
