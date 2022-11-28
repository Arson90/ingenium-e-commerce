package com.ingenium.ingeniumecommerce.constant;

public final class Constant {
    public static final class Address {
        public static final String CHARACTER_ONLY_REGEX = "\\D+";
        public static final String STREET_OR_APARTMENT_NUMBER_REGEX = "^[1-9]\\d{1,2}";
        public static final String POSTAL_CODE = "\\d{2}-\\d{3}";

        public static final String CHARACTER_ONLY_MESSAGE = "Only characters are required [A-Z, a-z]";
        public static final String STREET_OR_APARTMENT_NUMBER_MESSAGE = "Only digits are required [1-999]";
        public static final String POSTAL_CODE_MESSAGE = "Required format, example 12-345";
    }
}
