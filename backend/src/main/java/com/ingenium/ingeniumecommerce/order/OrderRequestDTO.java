package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import lombok.Getter;

import javax.validation.Valid;
import java.util.Set;

@Getter
public class OrderRequestDTO {
    @Valid
    private CustomerRequestDTO customerRequestDTO;
    @Valid
    private AddressRequestDTO addressRequestDTO;
    private PaymentType paymentType;
    private Set<CartEntryRequestDTO> cartEntriesRequestDTO;
}
