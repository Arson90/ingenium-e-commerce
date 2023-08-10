package com.ingenium.ingeniumecommerce.address;

import com.ingenium.ingeniumecommerce.account.data.AddressData;

public final class AddressFactoryUtils {
    private AddressFactoryUtils() {
    }

    public static Address convertAddressRequestDtoToAddress(final AddressRequestDTO addressRequestDTO) {
        return Address.builder()
                .streetName(addressRequestDTO.getStreetName())
                .streetNumber(addressRequestDTO.getStreetNumber())
                .apartmentNumber(addressRequestDTO.getApartmentNumber())
                .city(addressRequestDTO.getCity())
                .postalCode(addressRequestDTO.getPostalCode())
                .country(addressRequestDTO.getCountry())
                .build();
    }

    public static AddressResponseDTO convertAddressToAddressResponseDTO(final Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .streetName(address.getStreetName())
                .streetNumber(address.getStreetNumber())
                .apartmentNumber(address.getApartmentNumber())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }


    public static AddressData convertAddressToAddressData(final Address address) {
        return AddressData.builder()
                .streetName(address.getStreetName())
                .streetNumber(address.getStreetNumber())
                .apartmentNumber(address.getApartmentNumber())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
