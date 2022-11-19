-- liquibase formatted sql
-- changeset arek:8 runOnChange:true
CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    payment_type VARCHAR(20),
    total_price DECIMAL(10,2),
    PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT orders_customers_id
        FOREIGN KEY (customer_id) REFERENCES customers(id)