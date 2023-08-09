package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.constant.RequestParams;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class CustomerRequestDTO {
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.CHARACTER_ONLY_REGEX, message = RequestParams.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.CHARACTER_ONLY_REGEX, message = RequestParams.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(regexp = RequestParams.Customer.EMAIL_REGEX, message = RequestParams.Customer.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = RequestParams.Customer.PHONE_NUMBER_REGEX, message = RequestParams.Customer.PHONE_NUMBER_MESSAGE)
    private String phoneNumber;
}
