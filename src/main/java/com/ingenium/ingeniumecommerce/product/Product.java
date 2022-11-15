package com.ingenium.ingeniumecommerce.product;

import com.ingenium.ingeniumecommerce.money.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    @Embedded
    @AttributeOverride(name = "totalPrice", column = @Column(name = "price"))
    private Money price;

    public Product updateCurrentProduct(final ProductDTO productDTO) {
        this.productName = productDTO.getProductName();
        this.price = productDTO.getPrice();
        return this;
    }
    public ProductView toProductView() {
        return ProductView.builder()
                .id(this.id)
                .productName(this.productName)
                .price(this.price)
                .build();
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
