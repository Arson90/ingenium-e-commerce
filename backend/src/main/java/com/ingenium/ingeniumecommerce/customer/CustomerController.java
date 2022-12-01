package com.ingenium.ingeniumecommerce.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingenium")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity<CustomerView> getAddressById(@PathVariable Long customerId) {
        final CustomerView customerView = this.customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customerView);
    }
}