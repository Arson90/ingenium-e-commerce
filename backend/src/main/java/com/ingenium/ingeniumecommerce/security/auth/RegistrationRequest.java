package com.ingenium.ingeniumecommerce.security.auth;

import com.ingenium.ingeniumecommerce.constant.RequestParams;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class RegistrationRequest {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = RequestParams.DefaultRegex.ANY_WORD_CHARACTER_REGEX, message = RequestParams.DefaultRegex.ANY_WORD_CHARACTER_MESSAGE)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = RequestParams.User.PASSWORD_REGEX, message = RequestParams.User.PASSWORD_MESSAGE)
    private String password;

    @Valid
    private CustomerRequestDTO customerRequestDTO;
}
