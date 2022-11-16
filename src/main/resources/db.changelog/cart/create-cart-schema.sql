--liquibase formatted sql
--changeset arek:4 runOnChange:true

CREATE TABLE carts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);
