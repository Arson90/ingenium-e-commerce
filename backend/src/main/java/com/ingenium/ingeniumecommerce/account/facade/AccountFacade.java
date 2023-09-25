package com.ingenium.ingeniumecommerce.account.facade;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.account.data.OrderData;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;

import java.util.List;

public interface AccountFacade {
    AccountData getAccountData();

    List<OrderData> getMyOrders();

    void updateAccountCustomerData(final CustomerRequestDTO customerRequestDTO);

    void updateAccountAddressData(final AddressRequestDTO addressRequestDTO);

    void changeAccountPassword(final String password);
}
