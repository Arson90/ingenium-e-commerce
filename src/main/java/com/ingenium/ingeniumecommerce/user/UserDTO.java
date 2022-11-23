package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String password;
    private CustomerDTO customerDTO;
}