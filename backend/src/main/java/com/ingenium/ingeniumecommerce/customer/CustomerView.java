package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.AddressView;

public interface CustomerView {
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhoneNumber();
    AddressView getAddress();
}
