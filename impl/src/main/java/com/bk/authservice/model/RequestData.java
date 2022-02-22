package com.bk.authservice.model;

import com.bk.authservice.auth.handler.AuthenticationType;

import java.util.Map;

/**
 * Maintains request details on original request
 */
public class RequestData {
    private String requestId;
    private String accessURL;
    private AuthenticationType authenticationType;
    private Map authenticationSpecificData;
    public RequestData() {

    }

    public Map getAuthenticationSpecificData() {
        return authenticationSpecificData;
    }

    public void setAuthenticationSpecificData(Map authenticationSpecificData) {
        this.authenticationSpecificData = authenticationSpecificData;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccessURL() {
        return accessURL;
    }

    public void setAccessURL(String accessURL) {
        this.accessURL = accessURL;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }
}
