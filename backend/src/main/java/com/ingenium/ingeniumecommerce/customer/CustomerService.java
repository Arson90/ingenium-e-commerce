package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;

public interface CustomerService {
    Customer updateAccountCustomer(final Long customerId, final CustomerRequestDTO customerRequestDTO);

    Address updateAccountAddress(final Long customerId, final AddressRequestDTO addressRequestDTO);
}