package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtils;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Customer updateCustomer(final CustomerRequestDTO customerRequestDTO) {
        this.firstName = customerRequestDTO.getFirstName();
        this.lastName = customerRequestDTO.getLastName();
        this.email = customerRequestDTO.getEmail();
        this.phoneNumber = customerRequestDTO.getPhoneNumber();
        return this;
    }

    public Address updateAddress(final AddressRequestDTO addressRequestDTO) {
        this.address = AddressFactoryUtils.convertAddressRequestDtoToAddress(addressRequestDTO);
        return this.address;
    }

    public void addAddress(final Address address) {
        this.address = address;
    }
}
