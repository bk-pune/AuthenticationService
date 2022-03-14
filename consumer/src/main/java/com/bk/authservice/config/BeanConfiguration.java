package com.bk.authservice.config;

import com.bk.authservice.entity.YaasEntity;
import com.bk.authservice.handler.oidc.OIDCPolicy;
import com.bk.authservice.handler.usernamepassword.UsernamePasswordPolicy;
import com.bk.authservice.handler.x509.X509Policy;
import com.bk.authservice.policy.GlobalPolicy;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.policy.PolicyManagerImpl;
import com.bk.dao.core.DAORegistry;
import com.bk.dao.core.DAORegistryImpl;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Configuration
public class BeanConfiguration {

    private static final String ENTITY_PACKAGE = "com.bk";

    @Bean
    public DAORegistry getDaoRegistry() {
        Set<Class> entityClasses = getEntityClasses();
        return new DAORegistryImpl(entityClasses); // do it here, we don't want to add spring dependency on DAO
    }

    private Set<Class> getEntityClasses() {
        Reflections reflections = new Reflections(ENTITY_PACKAGE, Scanners.SubTypes);
        return reflections.getSubTypesOf(YaasEntity.class)
                .stream()
                .collect(Collectors.toSet());
    }


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