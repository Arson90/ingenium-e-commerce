package com.ingenium.ingeniumecommerce.orderEntry;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.order.Order;
import com.ingenium.ingeniumecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_entries")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderEntry(final Product product, final int quantity, final Order order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }

    public Money calculateEntryPrice() {
        return this.product.getPrice().multiply(this.quantity);
    }
}