package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import lombok.Getter;

import javax.validation.Valid;

@Getter
public class OrderRequestDTO {
    @Valid
    private CustomerRequestDTO customerRequestDTO;
    @Valid
    private AddressRequestDTO addressRequestDTO;
}
