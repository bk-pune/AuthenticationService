package com.bk.authservice.strategy;

import com.bk.authservice.handler.AuthenticationHandler;
import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.handler.Credentials;
import com.bk.authservice.identity.UserPrincipal;
import com.bk.authservice.identity.UserPrincipalBuilder;
import com.bk.authservice.identity.YaasPrincipal;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.util.Constants;
import com.bk.authservice.util.CookieUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.bk.authservice.util.CookieUtils.TOKEN_COOKIE_NAME;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 10/02/22
 */
@Component
public abstract class AbstractAuthenticationStrategy<C extends Credentials, P extends YaasPrincipal> implements AuthenticationStrategy {

    protected AuthenticationHandler authenticationHandler;
    protected PolicyManager policyManager;
    /**
     * Registers this authentication strategy to the Authentication Strategy Resolver.
     * @param authenticationHandler for this authentication strategy
     * @param authenticationStrategyResolver
     */
    public AbstractAuthenticationStrategy(AuthenticationHandler authenticationHandler, PolicyManager policyManager, AuthenticationStrategyResolver authenticationStrategyResolver) {
        this.authenticationHandler = authenticationHandler;
        this.policyManager = policyManager;
        authenticationStrategyResolver.registerAuthenticationStrategy(authenticationHandler.getAuthenticationType(), this);
    }

    protected void manageCookies(String tokenValue, HttpServletResponse httpServletResponse) {
        // set sec token as a cookie
        httpServletResponse.addCookie(CookieUtils.generateCookie(TOKEN_COOKIE_NAME, tokenValue));

        removePreAuthCookie(httpServletResponse);
    }
}