package com.ingenium.ingeniumecommerce.address;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponseDTO {
    private Long id;
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;
    private String country;
}
