package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.AddressDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private String name;
    private String login;
    private String password;
    private CustomerDTO customerDTO;
    private AddressDTO addressDTO;
}
