package com.ingenium.ingeniumecommerce.productEntry;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    public ProductEntry(final Product product, final int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public boolean isContainsProduct(final Product product) {
        return this.product.equals(product);
    }

    public Money calculateEntryPrice() {
        return this.product.getPrice().multiply(this.quantity);
    }

    public void increaseQuantity(final int quantity) {
        this.quantity += quantity;
    }
}
