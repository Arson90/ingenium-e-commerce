--liquibase formatted sql
--changeset arek:1 runOnChange:true

CREATE TABLE products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(128) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);