package com.bk.authservice.strategy;

import com.bk.authservice.entity.User;
import com.bk.authservice.entity.YaasEntity;
import com.bk.authservice.handler.AuthenticationHandler;
import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.handler.Credentials;
import com.bk.authservice.identity.UserPrincipal;
import com.bk.authservice.identity.UserPrincipalBuilder;
import com.bk.authservice.identity.YaasPrincipal;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.service.GenericDataProviderService;
import com.bk.authservice.util.Constants;
import com.bk.authservice.util.CookieUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bk.authservice.util.CookieUtils.TOKEN_COOKIE_NAME;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 10/02/22
 */
@Component
public abstract class AbstractAuthenticationStrategy<C extends Credentials, P extends YaasPrincipal> implements AuthenticationStrategy {

    protected AuthenticationHandler authenticationHandler;
    protected PolicyManager policyManager;
    protected GenericDataProviderService genericDataProviderService;

    /**
     * Registers this authentication strategy to the Authentication Strategy Resolver.
     * @param genericDataProviderService
     * @param authenticationHandler for this authentication strategy
     * @param authenticationStrategyResolver
     */
    public AbstractAuthenticationStrategy(GenericDataProviderService genericDataProviderService, AuthenticationHandler authenticationHandler, PolicyManager policyManager, AuthenticationStrategyResolver authenticationStrategyResolver) {
        this.genericDataProviderService = genericDataProviderService;
        this.authenticationHandler = authenticationHandler;
        this.policyManager = policyManager;
        authenticationStrategyResolver.registerAuthenticationStrategy(authenticationHandler.getAuthenticationType(), this);
    }

    protected void manageCookies(String tokenValue, HttpServletResponse httpServletResponse) {
        // set sec token as a cookie
        httpServletResponse.addCookie(CookieUtils.generateCookie(TOKEN_COOKIE_NAME, tokenValue));

        removePreAuthCookie(httpServletResponse);
    }

    public UserPrincipal buildPrincipal(Map authenticationAttributes, HttpServletRequest httpServletRequest) {
        UserPrincipalBuilder builder = new UserPrincipalBuilder(); //TODO when non-user principal type is supported, this should be refactored
        return builder.authenticationAttributes(authenticationAttributes)
                .authenticationType(AuthenticationType.OIDC)
                .name(authenticationAttributes.get(Constants.USERNAME).toString())
                .authorization(null) //TODO
                .user(getUser(authenticationAttributes)) // TODO fetch user from db and set here
                .locale(httpServletRequest.getLocale())
                .build();
    }

    private User getUser(Map authenticationAttributes) {
        Map<String, Object> fetchCriteria = new HashMap<>();
        String username = authenticationAttributes.get(Constants.USERNAME).toString();
        User user = null;
        fetchCriteria.put(Constants.USERNAME, username);
        List<YaasEntity> entities = genericDataProviderService.fetchWithCriteria(User.class, fetchCriteria);
        if (entities != null) {
            user = (User) entities.get(0);
        }
        if (user == null) {
            user = createNewUser(authenticationAttributes);
        }
        return user;
    }

    private User createNewUser(Map authenticationAttributes) {
        User user = new User();
        user.setUsername(authenticationAttributes.get(Constants.USERNAME).toString());
        user = (User) genericDataProviderService.persistSingle(user);
        return user;
    }
}