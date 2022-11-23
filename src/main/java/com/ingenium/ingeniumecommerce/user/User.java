package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.customer.Customer;
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
@Table(name = "users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public void setPassword(String password) {
        this.password = password;
    }

    public User changePassword(final String password) {
        this.password = password;
        return this;
    }
}