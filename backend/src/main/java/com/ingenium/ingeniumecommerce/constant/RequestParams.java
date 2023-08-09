package com.ingenium.ingeniumecommerce.constant;

public final class RequestParams {
    private RequestParams() {}

    public static final class Address {
        public static final String STREET_OR_APARTMENT_NUMBER_REGEX = "^[1-9]\\d{1,2}";
        public static final String POSTAL_CODE_REGEX = "\\d{2}-\\d{3}";
        public static final String STREET_OR_APARTMENT_NUMBER_MESSAGE = "Only digits are required [1-999]";
        public static final String POSTAL_CODE_MESSAGE = "Required format, example 12-345";
        private Address() {}
    }

    public static final class Customer {
        public static final String PHONE_NUMBER_REGEX = "^[1-9]\\d{8}";
        public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        public static final String PHONE_NUMBER_MESSAGE = "Required format, example '123456789'";
        public static final String EMAIL_MESSAGE = "Invalid email address";
        private Customer() {}

    }

    public static final class DefaultRegex {
        public static final String ANY_WORD_CHARACTER_REGEX = "\\w+";
        public static final String CHARACTER_ONLY_REGEX = "\\D+";
        public static final String ANY_WORD_CHARACTER_MESSAGE = "Any character or number is required [a-z, A-Z, 0-9]";
        public static final String CHARACTER_ONLY_MESSAGE = "Only characters are required [A-Z, a-z]";
        private DefaultRegex() {
        }
    }

    public static final class Product {
        public static final String PRICE_REGEX = "^[1-9]\\d{1,4}\\.\\d{1,2}";
        public static final String PRICE_MESSAGE = "Required format, example '967.15'";
        private Product() {}
    }

    public static final class User {
        public static final String PASSWORD_REGEX = "\\S{8,16}";
        public static final String PASSWORD_MESSAGE = "Any character, min. 8 and max. 16 characters are required";
        private User() {}
    }
}
