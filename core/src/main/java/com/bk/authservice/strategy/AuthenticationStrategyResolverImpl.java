package com.bk.authservice.strategy;

import com.bk.authservice.policy.GlobalPolicy;
import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.policy.PolicyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("authenticationStrategyResolver")
public class AuthenticationStrategyResolverImpl implements AuthenticationStrategyResolver {

    @Autowired
    private PolicyManager policyManager;

    private Map<AuthenticationType, AuthenticationStrategy> authenticationStrategyRegistry;

    public AuthenticationStrategyResolverImpl() {
        authenticationStrategyRegistry = new HashMap<>();
    }

    public void registerAuthenticationStrategy(AuthenticationType handler, AuthenticationStrategy authenticationStrategy) {
        authenticationStrategyRegistry.put(handler, authenticationStrategy);
    }

    public AuthenticationStrategy resolveAuthenticationStrategy() {
        GlobalPolicy policy = policyManager.getPolicy(GlobalPolicy.class);
        return authenticationStrategyRegistry.get(policy.getAuthenticationHandler());
    }
}
