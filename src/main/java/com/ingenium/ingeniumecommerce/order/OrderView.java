package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.user.UserView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderView {
    private Long id;
    private UserView userView;
    private PaymentType paymentType;
}