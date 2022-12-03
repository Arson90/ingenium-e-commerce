package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.validation.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product name is mandatory")
    @Pattern(regexp = Constant.DefaultRegex.ANY_WORD_CHARACTER_REGEX, message = Constant.DefaultRegex.ANY_WORD_CHARACTER_MESSAGE)
    private String productName;

    @Valid
    private Money price;
}
