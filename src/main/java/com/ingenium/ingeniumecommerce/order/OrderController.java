package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingenium")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}")
    ResponseEntity<OrderView> getOrderById(@PathVariable final Long orderId) {
        final OrderView orderView = this.orderService.findOrderById(orderId);
        return ResponseEntity.ok(orderView);
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderView>> getOrders() {
        final List<OrderView> orders = this.orderService.findAllOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/orders/{paymentType}")
    ResponseEntity<OrderView> createOrder(@RequestBody final OrderDTO orderDTO, @PathVariable final PaymentType paymentType,
                                          @CookieValue(value = "cartId", required = false) final String cartCookieId) {
        final OrderView orderView = this.orderService.createOrder(orderDTO, paymentType, cartCookieId);
        return ResponseEntity.ok(orderView);
    }
}