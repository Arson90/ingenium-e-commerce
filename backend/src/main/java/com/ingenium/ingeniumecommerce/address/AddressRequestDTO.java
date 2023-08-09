package com.ingenium.ingeniumecommerce.address;

import com.ingenium.ingeniumecommerce.constant.RequestParams;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class AddressRequestDTO {
    @NotBlank(message = "Street name is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.CHARACTER_ONLY_REGEX, message = RequestParams.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String streetName;

    @NotBlank(message = "Street number is mandatory")
    @Pattern(regexp = RequestParams.Address.STREET_OR_APARTMENT_NUMBER_REGEX, message = RequestParams.Address.STREET_OR_APARTMENT_NUMBER_MESSAGE)
    private String streetNumber;

    @Pattern(regexp = RequestParams.Address.STREET_OR_APARTMENT_NUMBER_REGEX, message = RequestParams.Address.STREET_OR_APARTMENT_NUMBER_MESSAGE)
    private String apartmentNumber;

    @NotBlank(message = "City is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.CHARACTER_ONLY_REGEX, message = RequestParams.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String city;

    @NotBlank(message = "Postal code is mandatory")
    @Pattern(regexp = RequestParams.Address.POSTAL_CODE_REGEX, message = RequestParams.Address.POSTAL_CODE_MESSAGE)
    private String postalCode;

    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.CHARACTER_ONLY_REGEX, message = RequestParams.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String country;
}
