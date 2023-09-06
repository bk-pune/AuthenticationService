package com.bk.authservice.filters;

import com.bk.authservice.strategy.AuthenticationStrategy;
import com.bk.authservice.strategy.AuthenticationStrategyResolverImpl;
import com.bk.authservice.util.CookieUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TicketValidationFilter implements Filter {
    // TODO - isBypassURL - get it from callback

    @Autowired
    private AuthenticationStrategyResolverImpl authenticationStrategyResolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if(isBypassUrl(httpServletRequest.getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String ticket = getTicket(httpServletRequest);
            if(ticket == null) { //TODO is ticket valid
                try {
                    AuthenticationStrategy authenticationStrategy = authenticationStrategyResolver.resolveAuthenticationStrategy();
                    authenticationStrategy.handleRequest(httpServletRequest, httpServletResponse);
                } catch (Exception e) {
                    e.printStackTrace(); // TODO add logger
                    throw new ServletException(e);
                }
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isBypassUrl(String requestPath) {
        List<String> bypassUrlList = Arrays.asList(
                "/favicon.ico", "/auth/usernamepassword/login",
                "/auth/usernamepassword/login.html",
                "/auth/usernamepassword/logout",
                "/auth/oidc/code",
                "/logout",
                "/postlogout");
        boolean isBypassUrl = false;
        for(String bypassUrl:bypassUrlList) {
            if(requestPath.contains(bypassUrl)) { //TODO replace with matcher
                isBypassUrl = true;
                break;
            }
        }
        return isBypassUrl;
    }

    private String getTicket(HttpServletRequest httpServletRequest) {
        Cookie cookie = CookieUtils.extractCookie(httpServletRequest, CookieUtils.TOKEN_COOKIE_NAME);
        if(cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

}
