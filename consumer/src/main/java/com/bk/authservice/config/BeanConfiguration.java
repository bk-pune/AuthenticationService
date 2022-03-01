package com.bk.authservice.config;

import com.bk.authservice.auth.handler.oidc.OIDCPolicy;
import com.bk.authservice.auth.handler.x509.X509Policy;
import com.bk.authservice.auth.policy.PolicyManager;
import com.bk.authservice.auth.handler.usernamepassword.UsernamePasswordPolicy;
import com.bk.authservice.policy.GlobalPolicy;
import com.bk.authservice.policy.PolicyManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

@Component
@Configuration
public class BeanConfiguration {

    @Bean
    public PolicyManager getPolicyManager() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PolicyManager policyManager = new PolicyManagerImpl();
        Properties prop = new Properties();
        prop.load(AuthStrategyBeanConfiguration.class.getClassLoader().getResourceAsStream("authentication.properties"));

        GlobalPolicy globalPolicy = policyManager.preparePolicyFromProperties(prop, GlobalPolicy.class);
        UsernamePasswordPolicy usernamePasswordPolicy = policyManager.preparePolicyFromProperties(prop, UsernamePasswordPolicy.class);
        OIDCPolicy oidcPolicy = policyManager.preparePolicyFromProperties(prop, OIDCPolicy.class);
        X509Policy x509Policy = policyManager.preparePolicyFromProperties(prop, X509Policy.class);

        policyManager.registerPolicy(GlobalPolicy.class, globalPolicy);
        policyManager.registerPolicy(UsernamePasswordPolicy.class, usernamePasswordPolicy);
        policyManager.registerPolicy(OIDCPolicy.class, oidcPolicy);
        policyManager.registerPolicy(X509Policy.class, x509Policy);

        return policyManager;
    }

}