package com.bk.authservice.handler.usernamepassword;

import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.identity.UserPrincipal;
import com.bk.authservice.identity.UserPrincipalBuilder;
import com.bk.authservice.model.MemCache;
import com.bk.authservice.model.RequestData;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.service.GenericDataProviderService;
import com.bk.authservice.strategy.AbstractAuthenticationStrategy;
import com.bk.authservice.strategy.AuthenticationStrategyResolver;
import com.bk.authservice.util.Constants;
import com.bk.authservice.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UsernamePasswordAuthenticationStrategy extends AbstractAuthenticationStrategy<UsernamePasswordCredentials, UserPrincipal> {

    public UsernamePasswordAuthenticationStrategy(GenericDataProviderService genericDataProviderService, PolicyManager policyManager, AuthenticationStrategyResolver authenticationStrategyResolver) {
        super(genericDataProviderService, new UsernamePasswordAuthenticationHandler(), policyManager, authenticationStrategyResolver);
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
        Map authenticationAttributes = null;

        try{
            authenticationAttributes = authenticationHandler.authenticate(usernamePasswordCredentials, policyManager.getPolicy(UsernamePasswordPolicy.class));
        } catch (Exception e) {
            e.printStackTrace();
            redirectForAuthentication(httpServletRequest, httpServletResponse);
            return;
        }

        // authentication success, generate authorization data
        // authorizationData = authorizationResolver.resolveAuthorization(username?)
        UserPrincipal userPrincipal = buildPrincipal(authenticationAttributes, httpServletRequest);

        // save authorization data to db/cache
        String tokenValue = preAuthCookie.getValue() + "_" +userPrincipal.getName(); // TODO

        // remove request data
        MemCache.removeRequestData(requestData.getRequestId());
        manageCookies(tokenValue, httpServletResponse);

        // redirect to original url
        httpServletResponse.sendRedirect(requestData.getAccessURL());
    }

    public UserPrincipal buildPrincipal(Map authenticationAttributes, HttpServletRequest httpServletRequest) {
        UserPrincipalBuilder builder = new UserPrincipalBuilder(); //TODO when non-user principal type is supported, this should be refactored
        UserPrincipal principal = builder.authenticationAttributes(authenticationAttributes)
                .authenticationType(AuthenticationType.USERNAME_PASSWORD)
                .name(authenticationAttributes.get(Constants.USERNAME).toString())
                .authorization(null) //TODO AuthorizationResolver
                .locale(httpServletRequest.getLocale())
                .build();
        // TODO - lots of items - persist only if not present in db
        genericDataProviderService.persistSingle(principal.getUser());
        return principal;
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
