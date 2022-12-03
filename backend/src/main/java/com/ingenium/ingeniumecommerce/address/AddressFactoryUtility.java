package com.ingenium.ingeniumecommerce.address;

public final class AddressFactoryUtility {
    private AddressFactoryUtility() {
    }

    public static Address convertAddressRequestDtoToAddress(final AddressRequestDTO addressRequestDTO) {
        return Address.builder()
                .streetName(addressRequestDTO.getStreetName())
                .streetNumber(addressRequestDTO.getStreetNumber())
                .apartmentNumber(addressRequestDTO.getApartmentNumber())
                .town(addressRequestDTO.getTown())
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
                .town(address.getTown())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }
}
