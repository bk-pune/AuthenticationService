package com.bk.authservice.handler.oidc;

import com.bk.authservice.handler.Credentials;

/**
 * Credential type for OpenID Connect authentication flow.
 */
public class OIDCCredentials implements Credentials {
    private String code;
    private String clientId;
    private String clientSecret;

    public OIDCCredentials() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
