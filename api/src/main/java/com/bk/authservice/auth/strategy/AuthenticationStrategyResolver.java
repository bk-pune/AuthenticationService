package com.bk.authservice.auth.strategy;

import com.bk.authservice.auth.handler.AuthenticationType;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/02/22
 */
public interface AuthenticationStrategyResolver {
    void registerAuthenticationStrategy(AuthenticationType handler, AuthenticationStrategy authenticationStrategy);
    AuthenticationStrategy resolveAuthenticationStrategy();
}
