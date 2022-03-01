package com.bk.authservice.auth.strategy;

import com.bk.authservice.auth.handler.Credentials;
import com.bk.authservice.auth.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public interface AuthenticationStrategy<C extends Credentials> {
    void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
    C extractCredentials(HttpServletRequest httpServletRequest) throws Exception;

    //Principal buildPrincipal(Map authenticationAttributes, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    default Cookie generatePreAuthCookie() {
        String randomUID = UUID.randomUUID().toString();
        Cookie preAuthCookie = CookieUtils.generateCookie(CookieUtils.PRE_AUTH_COOKIE_NAME, randomUID);
        return preAuthCookie;
    }

    default void removePreAuthCookie(HttpServletResponse httpServletResponse) {
        // invalidate the pre-auth cookie
        Cookie preAuthCookie = CookieUtils.generateCookie(CookieUtils.PRE_AUTH_COOKIE_NAME, null);
        preAuthCookie.setMaxAge(0);
        httpServletResponse.addCookie(preAuthCookie);
    }
}
