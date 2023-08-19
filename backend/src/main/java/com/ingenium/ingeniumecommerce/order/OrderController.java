package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.constant.RestApiUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(RestApiUrl.Page.ORDERS + "/{orderId}")
    ResponseEntity<OrderView> getOrderById(@PathVariable final Long orderId) {
        final OrderView orderView = this.orderService.findOrderById(orderId);
        return ResponseEntity.ok(orderView);
    }

    @GetMapping(RestApiUrl.Page.ORDERS)
    ResponseEntity<List<OrderView>> getOrders() {
        final List<OrderView> orders = this.orderService.findAllOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(RestApiUrl.Page.ORDERS)
    ResponseEntity<OrderResponseDTO> createOrder(@RequestBody final OrderRequestDTO orderRequestDTO) {
        final OrderResponseDTO orderResponseDTO = this.orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderResponseDTO);
    }
}