package com.ingenium.ingeniumecommerce.address;

public interface AddressView {
    Long getId();
    String getStreetName();
    String getStreetNumber();
    String getApartmentNumber();
    String getTown();
    String getPostalCode();
    String getCountry();
}
