package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerQueryRepository customerQueryRepository;

    @Override
    @Transactional
    public Customer updateAccountCustomer(final Long customerId, final CustomerRequestDTO customerRequestDTO) {
        return customerQueryRepository.findById(customerId)
                .map(customer -> customer.updateCustomer(customerRequestDTO))
                .orElseThrow(() -> CustomerNotFoundException.createForCartId(customerId));
    }

    @Override
    @Transactional
    public Address updateAccountAddress(final Long customerId, final AddressRequestDTO addressRequestDTO) {
        return customerQueryRepository.findById(customerId)
                .map(customer -> customer.updateAddress(addressRequestDTO))
                .orElseThrow(() -> CustomerNotFoundException.createForCartId(customerId));
    }
}