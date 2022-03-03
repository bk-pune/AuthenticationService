package com.bk.authservice.policy;

import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.util.Constants;

import java.util.Properties;

public class GlobalPolicy implements Policy {
    private AuthenticationType authenticationType;

    public GlobalPolicy() {
    }

    public AuthenticationType getAuthenticationHandler() {
        return authenticationType;
    }

    @Override
    public Policy loadFromProperties(Properties properties) {
        this.authenticationType = AuthenticationType.getHandler(properties.getProperty(Constants.AUTHENTICATION_HANDLER));
        return this;
    }
}
