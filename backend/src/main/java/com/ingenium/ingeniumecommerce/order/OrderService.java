package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.enumeration.PaymentType;

import java.util.List;

public interface OrderService {
    OrderView findOrderById(final Long orderId);
    List<OrderView> findAllOrders();
    OrderResponseDTO createOrder(final OrderRequestDTO orderRequestDTO, final PaymentType paymentType, final String cartCookieId);
}