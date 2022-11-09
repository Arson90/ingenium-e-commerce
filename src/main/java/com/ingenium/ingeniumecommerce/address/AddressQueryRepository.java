package com.ingenium.ingeniumecommerce.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressQueryRepository extends JpaRepository<Address, Long> {
    Optional<AddressView> findAddressById(Long addressId);
}
