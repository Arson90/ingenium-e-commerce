package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserView toAnonymousUserView() {
        return UserView.builder()
                .name(this.name)
                .customerView(this.customer.toCustomerView())
                .addressView(this.address.toAddressView())
                .build();
    }
}
