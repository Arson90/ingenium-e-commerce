package com.ingenium.ingeniumecommerce.order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(final String message){
        super(message);
    }

    public static OrderNotFoundException createForOrderId(final Long orderId) {
        return new OrderNotFoundException(String.format("Order not found, ID: %d", orderId));
    }
}