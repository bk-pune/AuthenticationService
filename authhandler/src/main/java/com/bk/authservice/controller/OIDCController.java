package com.bk.authservice.controller;

import com.bk.authservice.strategy.AuthenticationStrategyResolverImpl;
import com.bk.authservice.strategy.AuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/oidc")
public class OIDCController implements AuthenticationController {

    @Autowired
    private AuthenticationStrategyResolverImpl authenticationStrategyResolver;

    @Override
    @GetMapping(value = "/code")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        AuthenticationStrategy oidcAuthenticationStrategy = authenticationStrategyResolver.resolveAuthenticationStrategy();
        oidcAuthenticationStrategy.handleRequest(httpServletRequest, httpServletResponse);
    }
}
