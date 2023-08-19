package com.ingenium.ingeniumecommerce.order;

public final class OrderFactoryUtils {
    private OrderFactoryUtils() {
    }

    public static OrderResponseDTO convertOrderToOrderResponseDto(final Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .email(order.getCustomer().getEmail())
                .build();
    }
}
