package com.bk.authservice.resource;

import com.bk.authservice.auth.policy.Policy;
import com.bk.authservice.auth.policy.PolicyManager;
import com.bk.authservice.auth.util.CookieUtils;
import com.bk.authservice.policy.GlobalPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ResourceController {

    @Autowired
    private PolicyManager policyManager;

    @GetMapping(value="/hello")
    public String helloWorld(HttpServletRequest request, HttpServletResponse response) {
        return "<h1>Hello World</h1>";
    }

    @GetMapping(value="/")
    public String defaultMethod(HttpServletRequest request, HttpServletResponse response) {
        return "<h1>Authentication Service - Default Landing Page !</h1>"
                + "<br>"
                + "Authentication Handler: " + policyManager.getPolicy(GlobalPolicy.class).getAuthenticationHandler().getHandlerName() + "<br>"
                + "User information obtained from authentication handler: " + CookieUtils.extractCookie(request, CookieUtils.TOKEN_COOKIE_NAME).getValue();
    }

    @GetMapping(value="/policy/{policyType}")
    @ResponseBody
    public <T extends Policy> T getPolicy(@PathVariable String policyType) throws ClassNotFoundException {
        return (T) policyManager.getPolicy(GlobalPolicy.class); //TODO
    }
}
