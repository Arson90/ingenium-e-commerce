package com.ingenium.ingeniumecommerce.account.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressData {
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;
    private String country;
}
