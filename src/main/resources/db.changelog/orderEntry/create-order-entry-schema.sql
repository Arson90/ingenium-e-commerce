-- liquibase formatted sql
-- changeset arek:9 runOnChange:true
CREATE TABLE order_entries (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    order_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE order_entries
    ADD CONSTRAINT order_entries_products_id FOREIGN KEY (product_id) REFERENCES products(id),
    ADD CONSTRAINT order_entries_carts_id FOREIGN KEY (order_id) REFERENCES carts(id)