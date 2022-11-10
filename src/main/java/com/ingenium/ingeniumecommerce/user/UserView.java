package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserView {
    private Long id;
    private String name;
    private String login;
    private String password;
    private Customer customer;
    private Address address;
}
