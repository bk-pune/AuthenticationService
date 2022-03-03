package com.bk.authservice.handler.oidc;

import com.bk.authservice.policy.Policy;

import java.util.Properties;

public class OIDCPolicy implements Policy {

    private static final String PREFIX="oidc.";
    private String clientId;
    private String clientSecret;
    private String metadataEndpoint;
    private String authorizationCodeEndpoint;
    private String tokenEndpoint;
    private String singleLogoutEndpoint;
    private String userInfoEndpoint;
    private String issuer;
    private String authorizationCodeCallbackEndpoint;

    public OIDCPolicy() {
    }

    public OIDCPolicy loadFromProperties(Properties properties) {
        this.clientId = properties.getProperty(PREFIX+"clientId");
        this.clientSecret = properties.getProperty(PREFIX+"clientSecret");
        this.authorizationCodeEndpoint = properties.getProperty(PREFIX+"authorizationCodeEndpoint");
        this.authorizationCodeEndpoint = properties.getProperty(PREFIX+"authorizationCodeEndpoint");
        this.tokenEndpoint  = properties.getProperty(PREFIX + "tokenEndpoint");
        this.singleLogoutEndpoint = properties.getProperty(PREFIX+"singleLogoutEndpoint");
        this.metadataEndpoint = properties.getProperty(PREFIX+ "metadataEndpoint");
        this.userInfoEndpoint = properties.getProperty(PREFIX+"userInfoEndpoint");
        this.issuer = properties.getProperty(PREFIX + "issuer");
        this.authorizationCodeCallbackEndpoint = properties.getProperty(PREFIX + "authorizationCodeCallbackEndpoint");
        return this;
    }

    public String getAuthorizationCodeCallbackEndpoint() {
        return authorizationCodeCallbackEndpoint;
    }

    public void setAuthorizationCodeCallbackEndpoint(String authorizationCodeCallbackEndpoint) {
        this.authorizationCodeCallbackEndpoint = authorizationCodeCallbackEndpoint;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSingleLogoutEndpoint() {
        return singleLogoutEndpoint;
    }

    public void setSingleLogoutEndpoint(String singleLogoutEndpoint) {
        this.singleLogoutEndpoint = singleLogoutEndpoint;
    }

    public String getMetadataEndpoint() {
        return metadataEndpoint;
    }

    public void setMetadataEndpoint(String metadataEndpoint) {
        this.metadataEndpoint = metadataEndpoint;
    }

    public String getUserInfoEndpoint() {
        return userInfoEndpoint;
    }

    public void setUserInfoEndpoint(String userInfoEndpoint) {
        this.userInfoEndpoint = userInfoEndpoint;
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

    public String getAuthorizationCodeEndpoint() {
        return authorizationCodeEndpoint;
    }

    public void setAuthorizationCodeEndpoint(String authorizationCodeEndpoint) {
        this.authorizationCodeEndpoint = authorizationCodeEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }
}
