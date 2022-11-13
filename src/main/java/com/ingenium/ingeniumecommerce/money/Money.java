package com.ingenium.ingeniumecommerce.money;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {
    private final BigDecimal totalPrice;

    protected Money() {
        this.totalPrice = BigDecimal.ZERO;
    }

    public Money(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Money multiply(final int multiplier) {
        return new Money(totalPrice.multiply(BigDecimal.valueOf(multiplier)));
    }

    public Money add(final Money price) {
        return new Money(this.totalPrice.add(price.totalPrice));
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
