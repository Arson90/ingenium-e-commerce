--liquibase formatted sql
--changeset arek:8 runOnChange:true
insert into users (id, username, password, role, customer_id) values (1, 'admin', '$2a$12$EE52zrXeAfN2jJ4bqJ4knO0aGcHbJvr/NMSIDQMeRqCgnmdHc58wG','ROLE_ADMIN', null);
insert into users (id, username, password, role, customer_id) values (2, 'test', '$2a$10$VZDDMdwVuINRzpesYD/CGe3W4o8VrXHSrcytmsP.pOVo46GfSV1ZW','ROLE_USER', null);
