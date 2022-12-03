package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryFactoryUtils;

public final class OrderFactoryUtils {
    private OrderFactoryUtils() {
    }

    public static OrderResponseDTO convertOrderToOrderResponseDto(final Order order) {
        return OrderResponseDTO.builder()
                .customerResponseDTO(CustomerFactoryUtils
                        .convertCustomerToCustomerResponseDto(order.getCustomer()))
                .orderEntriesResponseDTO(OrderEntryFactoryUtils
                        .convertOrderEntriesToOrderEntriesResponseDTO(order.getOrderEntries()))
                .paymentType(order.getPaymentType())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
