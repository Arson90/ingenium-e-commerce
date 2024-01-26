-- liquibase formatted sql
-- changeset arek:9 runOnChange:true
CREATE TABLE order_notification(
    id               BIGINT  NOT NULL AUTO_INCREMENT,
    date             DATE,
    order_id         BIGINT  NOT NULL,
    email            VARCHAR(128),
    status           VARCHAR(25),
    is_lock          BIT,
    sending_attempts INTEGER,
    PRIMARY KEY (id)
);