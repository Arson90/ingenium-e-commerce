package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import com.ingenium.ingeniumecommerce.validation.Constant;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserRequestDTO {
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.ANY_WORD_CHARACTER_REGEX, message = Constant.DefaultRegex.ANY_WORD_CHARACTER_MESSAGE)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = Constant.User.PASSWORD_REGEX, message = Constant.User.PASSWORD_MESSAGE)
    private String password;

    @Valid
    private CustomerRequestDTO customerRequestDTO;
}
