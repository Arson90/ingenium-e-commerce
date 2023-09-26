package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;

import java.util.List;

public interface OrderService {
    OrderView findOrderById(final Long orderId);
    List<OrderView> findAllOrders();
    OrderResponseDTO createOrder(final OrderRequestDTO orderRequestDTO);
    List<Order> findMyOrders(final Customer customer);
}