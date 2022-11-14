package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.AddressDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderDTO {
    private CustomerDTO customerDTO;
    private AddressDTO addressDTO;
}
