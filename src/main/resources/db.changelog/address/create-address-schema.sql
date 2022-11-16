-- liquibase formatted sql
-- changeset arek:5 runOnChange:true
CREATE TABLE addresses (
  id BIGINT NOT NULL AUTO_INCREMENT,
  street_name VARCHAR(128) NOT NULL,
  street_number VARCHAR(10) NOT NULL,
  apartment_number VARCHAR(10),
  town VARCHAR(50) NOT NULL,
  postal_code VARCHAR(20) NOT NULL,
  country VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);