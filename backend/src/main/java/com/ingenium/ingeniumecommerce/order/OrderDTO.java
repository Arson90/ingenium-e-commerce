package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.AddressDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;

@Builder
@Getter
public class OrderDTO {
    @Valid
    private CustomerDTO customerDTO;
    @Valid
    private AddressDTO addressDTO;
}
