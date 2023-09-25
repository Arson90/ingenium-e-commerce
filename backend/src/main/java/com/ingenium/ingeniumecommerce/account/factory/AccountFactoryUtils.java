package com.ingenium.ingeniumecommerce.account.factory;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.account.data.OrderData;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtils;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.order.Order;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryFactoryUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AccountFactoryUtils {
    private AccountFactoryUtils() {
    }

    public static List<OrderData> convertOrderToOrderData(final List<Order> orders) {
        return orders.stream()
                .map(order -> OrderData.builder()
                        .orderId(order.getId())
                        .orderDate(order.getDate())
                        .customerData(CustomerFactoryUtils.convertCustomerToCustomerData(order.getCustomer()))
                        .addressData(AddressFactoryUtils.convertAddressToAddressData(order.getCustomer().getAddress()))
                        .orderEntriesData(OrderEntryFactoryUtils.convertOrderEntriesToOrderEntriesData(order.getOrderEntries()))
                        .totalPrice(order.getTotalPrice().getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public static AccountData convertToAccountData(final Customer customer) {
        return AccountData.builder()
                .customerData(CustomerFactoryUtils.convertCustomerToCustomerData(customer))
                .addressData(AddressFactoryUtils.convertAddressToAddressData(customer.getAddress()))
                .build();
    }
}
