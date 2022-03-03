package com.bk.authservice.strategy;

import com.bk.authservice.handler.AuthenticationType;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/02/22
 */
public interface AuthenticationStrategyResolver {
    void registerAuthenticationStrategy(AuthenticationType handler, AuthenticationStrategy authenticationStrategy);
    AuthenticationStrategy resolveAuthenticationStrategy();
}
