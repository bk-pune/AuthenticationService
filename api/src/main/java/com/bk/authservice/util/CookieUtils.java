package com.bk.authservice.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CookieUtils {
    public static final String TOKEN_COOKIE_NAME = "sec_token";
    public static final String PRE_AUTH_COOKIE_NAME = "pre_authentication";

    public static Cookie generateCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        return cookie;
    }
    public static Cookie extractCookie(HttpServletRequest httpServletRequest, String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies == null || cookies.length == 0) {
            return null;
        }
        List<Cookie> securityToken = Optional.of(Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).collect(Collectors.toList())).get();
        if(!securityToken.isEmpty()) {
            return securityToken.get(0);
        }
        return null;
    }
}
