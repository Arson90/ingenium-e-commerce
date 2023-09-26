package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.CustomerView;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryView;

import java.time.LocalDate;
import java.util.Set;

public interface OrderView {
    Long getId();
    LocalDate getDate();
    CustomerView getCustomer();
    Set<OrderEntryView> getOrderEntries();
    PaymentType getPaymentType();
    Money getTotalPrice();
}