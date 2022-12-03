package com.ingenium.ingeniumecommerce.address;

import com.ingenium.ingeniumecommerce.validation.Constant;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class AddressRequestDTO {
    @NotBlank(message = "Street name is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.CHARACTER_ONLY_REGEX, message = Constant.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String streetName;

    @NotBlank(message = "Street number is mandatory")
    @Pattern(regexp = Constant.Address.STREET_OR_APARTMENT_NUMBER_REGEX, message = Constant.Address.STREET_OR_APARTMENT_NUMBER_MESSAGE)
    private String streetNumber;

    @Pattern(regexp = Constant.Address.STREET_OR_APARTMENT_NUMBER_REGEX, message = Constant.Address.STREET_OR_APARTMENT_NUMBER_MESSAGE)
    private String apartmentNumber;

    @NotBlank(message = "Town is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.CHARACTER_ONLY_REGEX, message = Constant.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String town;

    @NotBlank(message = "Postal code is mandatory")
    @Pattern(regexp = Constant.Address.POSTAL_CODE_REGEX, message = Constant.Address.POSTAL_CODE_MESSAGE)
    private String postalCode;

    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.CHARACTER_ONLY_REGEX, message = Constant.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String country;
}
