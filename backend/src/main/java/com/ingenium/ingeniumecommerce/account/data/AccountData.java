package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountData {
    private String username;
    private CustomerData customerData;
    private AddressData addressData;
}