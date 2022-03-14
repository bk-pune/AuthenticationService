package com.bk.authservice.identity;

import com.bk.authservice.entity.User;
import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.util.Constants;

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
    private final User user;

    public UserPrincipal(AuthenticationType authenticationType, Map authenticationAttributes, Authorization authorization, User user, Locale locale) {
        this.authenticationType = authenticationType;
        this.authenticationAttributes = authenticationAttributes;
        this.authorization = authorization;
        this.name = user.getUsername();
        this.locale = locale;
        this.user = user;
    }

    private User createUser(Map authenticationAttributes) {
        User user = new User();
        user.setUsername(authenticationAttributes.get(Constants.USERNAME).toString());
        // user.setEmail(authenticationAttributes.get(Constants.EMAIL).toString());
        // TODO other attributes
        return user;
    }

    public User getUser() {
        return user;
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
