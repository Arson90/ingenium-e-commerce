package com.ingenium.ingeniumecommerce.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class AddressView {
    private Long id;
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String town;
    private String postalCode;
    private String country;
}
