package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerData {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
