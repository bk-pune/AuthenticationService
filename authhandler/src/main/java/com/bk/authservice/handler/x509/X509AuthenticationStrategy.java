package com.bk.authservice.handler.x509;

import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.identity.UserPrincipal;
import com.bk.authservice.identity.UserPrincipalBuilder;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.strategy.AbstractAuthenticationStrategy;
import com.bk.authservice.strategy.AuthenticationStrategyResolver;
import com.bk.authservice.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.UUID;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/02/22
 */
public class X509AuthenticationStrategy extends AbstractAuthenticationStrategy<X509Credentials, UserPrincipal> {

    public static final String JAVAX_SERVLET_REQUEST_X_509_CERTIFICATE = "javax.servlet.request.X509Certificate";

    /**
     * Registers this authentication strategy to the Authentication Strategy Resolver.
     *
     * @param policyManager
     * @param authenticationStrategyResolver
     */
    public X509AuthenticationStrategy(PolicyManager policyManager, AuthenticationStrategyResolver authenticationStrategyResolver) {
        super(new X509AuthenticationHandler(), policyManager, authenticationStrategyResolver);
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        X509Credentials x509Credentials = extractCredentials(httpServletRequest);
        Map authenticationAttributes = authenticationHandler.authenticate(x509Credentials, policyManager.getPolicy(X509Policy.class));

        // authentication success, generate authorization data
        // authorizationData = authorizationResolver.resolveAuthorization(username?)
        UserPrincipal userPrincipal = buildPrincipal(authenticationAttributes, httpServletRequest);

        // save authorization data to db/cache
        String tokenValue = UUID.randomUUID().toString() + "_" +userPrincipal.getName(); // TODO

        // redirect to original url, redirection is must as we want our cookie to be set on the browser
        // TODO extract parameters on original url and put them here before redirecting
        httpServletResponse.sendRedirect(httpServletRequest.getRequestURI());
    }

    @Override
    public X509Credentials extractCredentials(HttpServletRequest httpServletRequest) throws Exception {
        X509Certificate certificateChain[] = (X509Certificate[])httpServletRequest.getAttribute(JAVAX_SERVLET_REQUEST_X_509_CERTIFICATE);
        X509Credentials x509Credentials = new X509Credentials();
        x509Credentials.setCertificateChain(certificateChain);
        return x509Credentials;
    }

    public UserPrincipal buildPrincipal(Map authenticationAttributes, HttpServletRequest httpServletRequest) {
        UserPrincipalBuilder builder = new UserPrincipalBuilder(); //TODO when non-user principal type is supported, this should be refactored
        return builder.authenticationAttributes(authenticationAttributes)
                .authenticationType(AuthenticationType.X509)
                .name(authenticationAttributes.get(Constants.USERNAME).toString())
                .authorization(null) //TODO
                .locale(httpServletRequest.getLocale())
                .build();
    }
}
