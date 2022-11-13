package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.money.Money;
import com.ingenium.ingeniumecommerce.product.Product;
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
import java.math.BigDecimal;
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

    public String getId() {
        return String.valueOf(id);
    }

    public CartView toCartView() {
        return CartView.builder()
                .id(this.id)
                .productEntries(this.productEntries)
                .totalPrice(this.totalPrice)
                .build();
    }

    public Cart addProduct(final Product product, final int quantity) {
        if (isProductExistsInTheCart(product)) {
            increaseQuantityOfExistingProduct(product, quantity);
        } else {
            addNewProduct(product, quantity);
        }
        this.calculateTotalPrice();
        return this;
    }

    private boolean isProductExistsInTheCart(final Product product) {
        return this.productEntries.stream()
                .anyMatch(productEntry -> productEntry.isContainsProduct(product));
    }

    private void increaseQuantityOfExistingProduct(final Product product, final int quantity) {
        this.productEntries.stream()
                .filter(productEntry -> productEntry.isContainsProduct(product))
                .forEach(productEntry -> productEntry.increaseQuantity(quantity));
    }

    private void addNewProduct(final Product product, final int quantity) {
        final ProductEntry productEntry = new ProductEntry(product, quantity);
        this.productEntries.add(productEntry);
    }

    private void calculateTotalPrice() {
        this.totalPrice = this.productEntries.stream()
                .map(ProductEntry::calculateEntryPrice)
                .reduce(new Money(BigDecimal.ZERO), Money::add);
    }
}
