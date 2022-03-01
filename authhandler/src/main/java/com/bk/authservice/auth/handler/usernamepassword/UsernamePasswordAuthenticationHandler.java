package com.bk.authservice.auth.handler.usernamepassword;

import com.bk.authservice.auth.handler.AuthenticationHandler;
import com.bk.authservice.auth.handler.AuthenticationType;
import com.bk.authservice.auth.policy.PolicyManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UsernamePasswordAuthenticationHandler implements AuthenticationHandler<UsernamePasswordCredentials, UsernamePasswordPolicy> {

    @Autowired
    private PolicyManager policyManager;

    @Override
    public Map authenticate(UsernamePasswordCredentials usernamePasswordCredentials, UsernamePasswordPolicy policy) throws Exception {
        if(usernamePasswordCredentials.getUsername().equals("bhushan") && usernamePasswordCredentials.getPassword().equals("bkpune")) {
         Map<String, String> attributes = new HashMap<>();   
         attributes.put("username", usernamePasswordCredentials.getUsername());
         return attributes;
        } else {
            // TODO throw AuthenticationException
            throw new Exception("Authentication Failed. Incorrect username or password!");
        }
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return AuthenticationType.USERNAME_PASSWORD;
    }
}
