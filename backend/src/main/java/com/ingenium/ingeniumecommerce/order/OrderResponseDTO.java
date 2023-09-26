package com.ingenium.ingeniumecommerce.order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderResponseDTO {
    private Long orderId;
    private String email;
}
