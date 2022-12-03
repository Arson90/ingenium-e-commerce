package com.ingenium.ingeniumecommerce.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCommandRepository extends JpaRepository<Address, Long> {
}
