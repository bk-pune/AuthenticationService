package com.bk.authservice.auth.strategy;

import com.bk.authservice.auth.handler.AuthenticationHandler;
import com.bk.authservice.auth.handler.Credentials;
import com.bk.authservice.auth.policy.PolicyManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 10/02/22
 */
@Component
public abstract class AbstractAuthenticationStrategy<T extends AuthenticationHandler, C extends Credentials> implements AuthenticationStrategy {

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
}
