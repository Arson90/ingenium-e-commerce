-- liquibase formatted sql
-- changeset arek:2 runOnChange:true
CREATE TABLE cart_entries (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    cart_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE cart_entries
    ADD CONSTRAINT cart_entries_products_id FOREIGN KEY (product_id) REFERENCES products(id),
    ADD CONSTRAINT cart_entries_carts_id FOREIGN KEY (cart_id) REFERENCES carts(id)