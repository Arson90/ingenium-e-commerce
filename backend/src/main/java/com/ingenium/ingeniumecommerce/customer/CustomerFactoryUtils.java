package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtility;

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

    public static CustomerResponseDTO convertCustomerToCustomerResponseDto(final Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .addressResponseDTO(AddressFactoryUtility
                        .convertAddressToAddressResponseDTO(customer.getAddress()))
                .build();
    }
}
