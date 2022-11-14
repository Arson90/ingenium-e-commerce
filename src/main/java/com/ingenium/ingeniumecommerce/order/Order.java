package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.productEntry.ProductEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProductEntry> productEntries;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    public OrderView toOrderView() {
        return OrderView.builder()
                .id(this.id)
                .customerView(this.customer.toCustomerView())
                .productEntries(this.productEntries)
                .paymentType(this.paymentType)
                .build();
    }
}
