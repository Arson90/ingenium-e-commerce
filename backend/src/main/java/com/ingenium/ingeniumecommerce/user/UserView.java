package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.CustomerView;

public interface UserView {
    Long getId();
    String getUsername();
    String getRole();
    CustomerView getCustomer();
}
