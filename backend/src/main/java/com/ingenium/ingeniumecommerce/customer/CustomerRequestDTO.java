package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.validation.Constant;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class CustomerRequestDTO {
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.CHARACTER_ONLY_REGEX, message = Constant.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.CHARACTER_ONLY_REGEX, message = Constant.DefaultRegex.CHARACTER_ONLY_MESSAGE)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(regexp = Constant.Customer.EMAIL_REGEX, message = Constant.Customer.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = Constant.Customer.PHONE_NUMBER_REGEX, message = Constant.Customer.PHONE_NUMBER_MESSAGE)
    private String phoneNumber;
}
