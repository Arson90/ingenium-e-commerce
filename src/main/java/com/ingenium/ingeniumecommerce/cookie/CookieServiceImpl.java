package com.ingenium.ingeniumecommerce.cookie;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieService{
    private static final int SECONDS_IN_DAY = 86400;
    private static final String CART_COOKIE_PATH = "http://localhost:8080/fexmon/cart";

    public void createCookieForCart(final String cartCookieId, final HttpServletResponse response) {
        final Cookie cookie = new Cookie("cartId", cartCookieId);
        cookie.setPath(CART_COOKIE_PATH);
        cookie.setMaxAge(SECONDS_IN_DAY * 5); // 5 days
        response.addCookie(cookie);
    }
}