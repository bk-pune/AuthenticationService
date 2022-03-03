package com.bk.authservice.controller;

import com.bk.authservice.util.Constants;
import com.bk.authservice.util.CookieUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/02/22
 */
@RestController
public class LogoutController {
    @GetMapping(Constants.LOGOUT_URL)
    public void logout(String postLogoutURL, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = CookieUtils.generateCookie(CookieUtils.TOKEN_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect(Constants.POST_LOGOUT_URL);
    }

    @GetMapping(value=Constants.POST_LOGOUT_URL)
    public String postLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "<h1>Logout successful.</h1>" +
                "<a href=\"" + Constants.HOME_URL +"\"> Click here</a>" + " to login again.";
    }
}
