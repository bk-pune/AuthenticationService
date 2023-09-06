package com.bk.authservice.strategy;

import com.bk.authservice.handler.Credentials;
import com.bk.authservice.identity.YaasPrincipal;
import com.bk.authservice.util.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public interface AuthenticationStrategy<C extends Credentials, P extends YaasPrincipal> {
    void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
    C extractCredentials(HttpServletRequest httpServletRequest) throws Exception;

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
