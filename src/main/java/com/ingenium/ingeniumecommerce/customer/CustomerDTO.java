package com.ingenium.ingeniumecommerce.customer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
