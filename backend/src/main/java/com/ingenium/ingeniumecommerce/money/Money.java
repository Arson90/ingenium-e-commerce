package com.ingenium.ingeniumecommerce.money;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Embeddable
public class Money {
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private final BigDecimal price;

    protected Money() {
        this.price = BigDecimal.ZERO;
    }

    public Money(BigDecimal price) {
        this.price = price;
    }

    public Money multiply(final int multiplier) {
        return new Money(price.multiply(BigDecimal.valueOf(multiplier)));
    }

    public Money add(final Money price) {
        return new Money(this.price.add(price.price));
    }

    public BigDecimal getPrice() {
        return price;
    }
}
