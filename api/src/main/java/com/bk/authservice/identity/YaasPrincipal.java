package com.bk.authservice.identity;

import com.bk.authservice.handler.AuthenticationType;

import java.security.Principal;
import java.util.Map;

/**
 * Represents an Identity after the authentication is complete.<br>
 * Principal can represent a user, a service or an organization; based on the credentials used while authentication.
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 02/03/22
 */
public interface YaasPrincipal extends Principal {
    /**
     * Get the authentication handler used for authentication of this principal.
     * @return Authentication type
     */
    AuthenticationType getAuthenticationType();

    /**
     * Returns <b>unmodifiable map</b> of principal attributes.<br>
     * These attributes include authentication handler being used, region details, language preferences and so on.
     * @return
     */
    Map getAuthenticationAttributes();

    /**
     * Returns the authorization of this Principal.
     * @return
     */
    Authorization getAuthorization();
}
