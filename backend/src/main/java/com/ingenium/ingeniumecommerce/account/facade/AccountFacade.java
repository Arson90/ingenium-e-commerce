package com.ingenium.ingeniumecommerce.account.facade;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;

public interface AccountFacade {
    AccountData getAccountData();

    void updateAccountCustomerData(final CustomerRequestDTO customerRequestDTO);

    void updateAccountAddressData(final AddressRequestDTO addressRequestDTO);

    void changeAccountPassword(final String password);
}
