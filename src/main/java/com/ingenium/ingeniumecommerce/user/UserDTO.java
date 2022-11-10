package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;
import lombok.Getter;

@Getter
public class UserDTO {
    private String name;
    private String login;
    private String password;
    private Customer customer;
    private Address address;
}
