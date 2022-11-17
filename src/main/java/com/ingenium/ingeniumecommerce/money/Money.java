package com.ingenium.ingeniumecommerce.money;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {
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
