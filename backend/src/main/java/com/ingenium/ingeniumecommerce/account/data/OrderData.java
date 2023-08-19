package com.ingenium.ingeniumecommerce.account.data;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
public class OrderData {
    private Long orderId;
    private LocalDate orderDate;
    private CustomerData customerData;
    private AddressData addressData;
    private Set<OrderEntryData> orderEntriesData;
    private BigDecimal totalPrice;
}
