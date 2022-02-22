package com.bk.authservice.auth.handler;


import com.bk.authservice.auth.policy.Policy;

import java.util.Map;

public interface AuthenticationHandler<C extends Credentials, P extends Policy> {
    /**
     * Authenticates the given credentials
     * @param  credentials
     * @return User Attributes
     */
    Map authenticate(C credentials, P policy) throws Exception;

    AuthenticationType getAuthenticationType();
}
