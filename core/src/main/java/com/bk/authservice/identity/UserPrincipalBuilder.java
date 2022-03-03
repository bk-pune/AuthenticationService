package com.bk.authservice.identity;

import com.bk.authservice.handler.AuthenticationType;

import java.util.Locale;
import java.util.Map;

/**
 * Builds the UserPrincipal using given values.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/03/22
 */
public class UserPrincipalBuilder {
    private AuthenticationType authenticationType;
    private Map authenticationAttributes;
    private Authorization authorization;
    private String name;
    private Locale locale;

    public UserPrincipalBuilder() {
    }

    public UserPrincipalBuilder authenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
        return this;
    }
    public UserPrincipalBuilder authenticationAttributes(Map authenticationAttributes) {
        this.authenticationAttributes = authenticationAttributes;
        return this;
    }
    public UserPrincipalBuilder authorization(Authorization authorization) {
        this.authorization = authorization;
        return this;
    }
    public UserPrincipalBuilder name(String name) {
        this.name = name;
        return this;
    }
    public UserPrincipalBuilder locale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public UserPrincipal build(){
        return new UserPrincipal(authenticationType, authenticationAttributes, authorization, name, locale);
    }
}
