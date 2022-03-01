package com.bk.authservice.auth.handler.usernamepassword;

import com.bk.authservice.auth.handler.AuthenticationType;
import com.bk.authservice.auth.policy.PolicyManager;
import com.bk.authservice.auth.strategy.AbstractAuthenticationStrategy;
import com.bk.authservice.auth.strategy.AuthenticationStrategyResolver;
import com.bk.authservice.auth.util.Constants;
import com.bk.authservice.auth.util.CookieUtils;
import com.bk.authservice.model.MemCache;
import com.bk.authservice.model.RequestData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UsernamePasswordAuthenticationStrategy extends AbstractAuthenticationStrategy<UsernamePasswordCredentials> {

    public UsernamePasswordAuthenticationStrategy(PolicyManager policyManager, AuthenticationStrategyResolver authenticationStrategyResolver) {
        super(new UsernamePasswordAuthenticationHandler(), policyManager, authenticationStrategyResolver);
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Cookie preAuthCookie = CookieUtils.extractCookie(httpServletRequest, CookieUtils.PRE_AUTH_COOKIE_NAME);
        if(preAuthCookie == null || MemCache.getRequestData(preAuthCookie.getName()) == null) {
            redirectForAuthentication(httpServletRequest, httpServletResponse);
            return;
        }
        RequestData requestData = MemCache.getRequestData(preAuthCookie.getName());

        // create instance of Credentials
        UsernamePasswordCredentials usernamePasswordCredentials = extractCredentials(httpServletRequest);
        Map userAttributes = authenticationHandler.authenticate(usernamePasswordCredentials, policyManager.getPolicy(UsernamePasswordPolicy.class));

        // remove request data
        MemCache.removeRequestData(requestData.getRequestId());

        // authentication success, generate authorization data
        // authorizationData = authorizationResolver.resolveAuthorization(username?)
        // save authorization data to db/cache
        String tokenValue = preAuthCookie.getValue() + "_" +userAttributes.get("username"); // TODO
        manageCookies(tokenValue, httpServletResponse);

        // redirect to original url
        httpServletResponse.sendRedirect(requestData.getAccessURL());
    }

    @Override
    public UsernamePasswordCredentials extractCredentials(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter(Constants.USERNAME);
        String password = httpServletRequest.getParameter(Constants.PASS_WORD);
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        return credentials;
    }

    private void redirectForAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // create temp cookie for maintaining track
        Cookie preAuthCookie = generatePreAuthCookie();

        // add request data to cache
        RequestData requestData = new RequestData();
        requestData.setAuthenticationType(AuthenticationType.USERNAME_PASSWORD);
        requestData.setRequestId(preAuthCookie.getName());
        requestData.setAccessURL(httpServletRequest.getServletPath());
        MemCache.putRequestData(requestData.getRequestId(), requestData);

        // add cookie on response
        httpServletResponse.addCookie(preAuthCookie);

        // redirect
        httpServletResponse.sendRedirect(Constants.LOGIN_PAGE);
    }
}
