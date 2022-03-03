package com.bk.authservice.config;

import com.bk.authservice.handler.x509.X509AuthenticationStrategy;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.strategy.AuthenticationStrategyResolver;
import com.bk.authservice.handler.oidc.OIDCAuthenticationStrategy;
import com.bk.authservice.handler.usernamepassword.UsernamePasswordAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AuthStrategyBeanConfiguration {

    @Autowired
    private AuthenticationStrategyResolver authenticationStrategyResolver;

    @Autowired
    private PolicyManager policyManager;

    @Bean
    public UsernamePasswordAuthenticationStrategy getUsernamePasswordAuthenticationStrategy() {
        return new UsernamePasswordAuthenticationStrategy(policyManager, authenticationStrategyResolver);
    }

    @Bean
    public OIDCAuthenticationStrategy getOIDCAuthenticationStrategy() {
        return new OIDCAuthenticationStrategy(policyManager, authenticationStrategyResolver);
    }

    @Bean
    public X509AuthenticationStrategy getX509AuthenticationStrategy() {
        return new X509AuthenticationStrategy(policyManager, authenticationStrategyResolver);
    }
}
