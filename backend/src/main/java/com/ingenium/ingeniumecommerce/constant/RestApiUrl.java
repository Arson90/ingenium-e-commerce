package com.ingenium.ingeniumecommerce.constant;

public final class RestApiUrl {
    public static final String PREFIX = "/ingenium";
    private RestApiUrl() {}

    public static final class Page {
        public static final String MY_ACCOUNT = PREFIX + "/account";
        public static final String ORDERS = PREFIX + "/orders";
        public static final String LOGIN = PREFIX + "/authenticate";
        public static final String REGISTER = PREFIX + "/register";
        private Page() {}
    }
}