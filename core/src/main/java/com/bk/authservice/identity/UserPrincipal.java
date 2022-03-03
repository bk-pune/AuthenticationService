package com.bk.authservice.identity;

import com.bk.authservice.handler.AuthenticationType;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/**
 * Represents a user principal.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/03/22
 */
public final class UserPrincipal implements YaasPrincipal {
    private final AuthenticationType authenticationType;
    private final Map authenticationAttributes;
    private final Authorization authorization;
    private final String name;
    private final Locale locale;

    public UserPrincipal(AuthenticationType authenticationType, Map authenticationAttributes, Authorization authorization, String name, Locale locale) {
        this.authenticationType = authenticationType;
        this.authenticationAttributes = authenticationAttributes;
        this.authorization = authorization;
        this.name = name;
        this.locale = locale;
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    @Override
    public Map getAuthenticationAttributes() {
        return Collections.unmodifiableMap(authenticationAttributes);
    }

    @Override
    public Authorization getAuthorization() {
        return authorization;
    }

    @Override
    public String getName() {
        return name;
    }

    public Locale getLocale() {
        return locale;
    }
}
