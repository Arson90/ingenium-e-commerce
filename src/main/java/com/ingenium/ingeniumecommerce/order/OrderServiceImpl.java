package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressDTO;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtility;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.user.User;
import com.ingenium.ingeniumecommerce.user.UserDTO;
import com.ingenium.ingeniumecommerce.user.UserFactoryUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderCommandRepository orderCommandRepository;

    public OrderServiceImpl(OrderCommandRepository orderCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
    }

    @Override
    public Order createOrder(final OrderDTO orderDTO) {
        final UserDTO userDTO = orderDTO.getUserDTO();
        final Customer customer = toCustomer(userDTO.getCustomerDTO());
        final Address address = toAddress(userDTO.getAddressDTO());
        final User anonymousUser = toUser(customer, address);
        final Order order = toOrder(anonymousUser);
        return this.orderCommandRepository.save(order);
    }

    private Customer toCustomer(final CustomerDTO customerDTO) {
        return CustomerFactoryUtils.createCustomer(customerDTO);
    }

    private Address toAddress(final AddressDTO addressDTO) {
        return AddressFactoryUtility.createAddress(addressDTO);
    }

    private User toUser(final Customer customer, final Address address) {
        return UserFactoryUtils.createAnonymousUser(customer, address);
    }

    private Order toOrder(final User anonymousUser) {
        return OrderFactoryUtils.createOrder(anonymousUser);
    }
}
