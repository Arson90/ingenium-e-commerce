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
}
