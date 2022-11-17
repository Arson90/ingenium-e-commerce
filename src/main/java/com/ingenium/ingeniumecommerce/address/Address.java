package com.ingenium.ingeniumecommerce.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private String streetNumber;
    private String apartmentNumber;
    private String town;
    private String postalCode;
    private String country;

    public AddressView toAddressView() {
        return AddressView.builder()
                .id(this.id)
                .streetName(this.streetName)
                .streetNumber(this.streetNumber)
                .apartmentNumber(this.apartmentNumber)
                .town(this.town)
                .postalCode(this.postalCode)
                .country(this.country)
                .build();
    }
}
