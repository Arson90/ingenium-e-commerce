package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.productEntry.ProductEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProductEntry> productEntries;
    @Embedded
    private Money totalPrice;
}
