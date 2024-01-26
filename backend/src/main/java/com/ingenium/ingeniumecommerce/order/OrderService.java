package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.orderNotification.OrderNotification;

import java.util.List;

public interface OrderService {
    OrderView findOrderById(final Long orderId);
    List<OrderView> findAllOrders();
    OrderNotification createOrder(final OrderRequestDTO orderRequestDTO);
    List<Order> findMyOrders(final Customer customer);
}