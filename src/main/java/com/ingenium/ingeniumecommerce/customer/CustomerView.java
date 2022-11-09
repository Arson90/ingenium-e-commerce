package com.ingenium.ingeniumecommerce.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerView {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
