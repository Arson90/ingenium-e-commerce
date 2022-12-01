package com.ingenium.ingeniumecommerce.cookie;

import javax.servlet.http.HttpServletResponse;

public interface CookieService {
    void createCookieForCart(final String cartCookieId, final HttpServletResponse response);
}