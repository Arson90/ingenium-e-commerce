package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.CustomerView;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderView {
    private Long id;
    private CustomerView customerView;
    private Set<OrderEntryView> orderEntryView;
    private PaymentType paymentType;
    private Money totalPrice;
}