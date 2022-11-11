package com.ingenium.ingeniumecommerce.address;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class AddressDTO {
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String town;
    private String postalCode;
    private String country;
}
