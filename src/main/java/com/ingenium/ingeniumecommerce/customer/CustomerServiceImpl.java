package com.ingenium.ingeniumecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerQueryRepository customerQueryRepository;

    public CustomerServiceImpl(CustomerQueryRepository customerQueryRepository) {
        this.customerQueryRepository = customerQueryRepository;
    }

    @Override
    public CustomerView findCustomerById(final Long customerId) {
        return customerQueryRepository.findCustomerById(customerId)
                .orElseThrow(() -> CustomerNotFoundException.createForCartId(customerId));
    }
}