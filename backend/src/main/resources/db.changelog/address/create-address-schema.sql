-- liquibase formatted sql
-- changeset arek:3 runOnChange:true
CREATE TABLE addresses (
  id BIGINT NOT NULL AUTO_INCREMENT,
  street_name VARCHAR(128),
  street_number VARCHAR(10),
  apartment_number VARCHAR(10),
  town VARCHAR(50),
  postal_code VARCHAR(20),
  country VARCHAR(50),
  PRIMARY KEY (id)
);