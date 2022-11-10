package com.ingenium.ingeniumecommerce.address;

public final class AddressFactoryUtility {
    private AddressFactoryUtility() {
    }

    public static Address createAddress(final AddressDTO addressDTO) {
        return Address.builder()
                .streetName(addressDTO.getStreetName())
                .streetNumber(addressDTO.getStreetNumber())
                .apartmentNumber(addressDTO.getApartmentNumber())
                .town(addressDTO.getTown())
                .postalCode(addressDTO.getPostalCode())
                .country(addressDTO.getCountry())
                .build();
    }
}
