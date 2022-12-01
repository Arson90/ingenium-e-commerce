package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.cart.Cart;
import com.ingenium.ingeniumecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "cart_entries")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
    private int quantity;

    public CartEntry(final Product product, final int quantity, final Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
    }

    public CartEntryView toCartEntryView() {
        return CartEntryView.builder()
                .productView(this.product.toProductView())
                .quantity(this.quantity)
                .build();
    }

    public boolean isContainsProduct(final Product product) {
        return this.product.equals(product);
    }

    public boolean isContainsProductById(final Long productId) {
        return this.product.getId().equals(productId);
    }

    public void increaseQuantity(final int quantity) {
        this.quantity += quantity;
    }
}
