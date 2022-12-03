package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.CustomerResponseDTO;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class OrderResponseDTO {
    private CustomerResponseDTO customerResponseDTO;
    private Set<OrderEntryResponseDTO> orderEntriesResponseDTO;
    private PaymentType paymentType;
    private Money totalPrice;
}
