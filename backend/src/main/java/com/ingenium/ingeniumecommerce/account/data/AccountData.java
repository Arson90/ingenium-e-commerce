package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountData {
    private CustomerData customerData;
    private AddressData addressData;
}