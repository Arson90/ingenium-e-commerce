package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntryView;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartEntry> cartEntries;
    
    public Cart addProduct(final Product product, final int quantity) {
        if (isProductExistsInTheCart(product)) {
            increaseQuantityOfExistingProduct(product, quantity);
        } else {
            addNewProduct(product, quantity);
        }
        return this;
    }

    public boolean deleteProductById(final Long productId) {
        return this.cartEntries.removeIf(cartEntry -> cartEntry.isContainsProductById(productId));
    }

    public CartView toCartView() {
        return CartView.builder()
                .id(this.id)
                .cartEntryView(toCartEntryView())
                .build();
    }

    public String getId() {
        return String.valueOf(id);
    }

    public Set<CartEntry> getCartEntries() {
        return cartEntries;
    }

    private Set<CartEntryView> toCartEntryView() {
        final Set<CartEntryView> entries = new HashSet<>();
        for (CartEntry cartEntry : this.cartEntries) {
            entries.add(cartEntry.toCartEntryView());
        }
        return entries;
    }

    private boolean isProductExistsInTheCart(final Product product) {
        return this.cartEntries != null && this.cartEntries.stream()
                .anyMatch(cartEntry -> cartEntry.isContainsProduct(product));
    }

    private void increaseQuantityOfExistingProduct(final Product product, final int quantity) {
        this.cartEntries.stream()
                .filter(cartEntry -> cartEntry.isContainsProduct(product))
                .forEach(cartEntry -> cartEntry.increaseQuantity(quantity));
    }

    private void addNewProduct(final Product product, final int quantity) {
        final CartEntry cartEntry = new CartEntry(product, quantity, this);
        if (this.cartEntries == null) {
            this.cartEntries = new HashSet<>();
        }
        this.cartEntries.add(cartEntry);
    }
}
