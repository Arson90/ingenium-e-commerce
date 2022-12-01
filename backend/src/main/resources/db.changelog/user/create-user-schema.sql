-- liquibase formatted sql
-- changeset arek:9 runOnChange:true
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `role` VARCHAR(128) NOT NULL,
    customer_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT users_customers_id
    FOREIGN KEY (customer_id) REFERENCES customers(id)