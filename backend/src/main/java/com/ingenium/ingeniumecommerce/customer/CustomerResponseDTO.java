package com.ingenium.ingeniumecommerce.customer;

import com.ingenium.ingeniumecommerce.address.AddressResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressResponseDTO addressResponseDTO;
}
