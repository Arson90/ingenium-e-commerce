-- liquibase formatted sql
-- changeset arek:4 runOnChange:true
CREATE TABLE customers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(128) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  email VARCHAR(128) NOT NULL,
  phone_number VARCHAR(128) NOT NULL,
  address_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE customers
    ADD CONSTRAINT customers_addresses_id
    FOREIGN KEY (address_id) REFERENCES addresses(id)