package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntry;
import com.ingenium.ingeniumecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderEntry> orderEntries;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "total_price"))
    private Money totalPrice;

    public void addCartEntriesToOrderEntries(final Map<Product, Integer> cartEntries) {
        this.orderEntries = new HashSet<>();
        for (Map.Entry<Product, Integer> entry : cartEntries.entrySet()) {
            final OrderEntry orderEntry = new OrderEntry(entry.getKey(), entry.getValue(), this);
            this.orderEntries.add(orderEntry);
        }
    }

    public void addCustomerToOrder(final Customer customer) {
        this.customer = customer;
    }

    public void addPaymentTypeToOrder(final PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void calculateTotalPrice() {
        this.totalPrice = this.orderEntries.stream()
                .map(OrderEntry::calculateEntryPrice)
                .reduce(new Money(BigDecimal.ZERO), Money::add);
    }
}
