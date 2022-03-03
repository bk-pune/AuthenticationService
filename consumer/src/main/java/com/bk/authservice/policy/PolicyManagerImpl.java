package com.bk.authservice.policy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * A sample implementation of PolicyManager that maintains the policies for different authentication configuration.<br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 22/02/22
 */
public class PolicyManagerImpl implements PolicyManager {

    private Map<Class<? extends Policy>, Policy> policyRegistry;

    public PolicyManagerImpl() {
        policyRegistry = new HashMap<>();
    }

    @Override
    public <T extends Policy> T getPolicy(Class<T> policyClass) {
        return (T) policyRegistry.get(policyClass);
    }

    @Override
    public <T extends Policy> void registerPolicy(Class<T> policyClass, T policy) {
        policyRegistry.put(policyClass, policy);
    }

    @Override
    public <T extends Policy> T preparePolicyFromProperties(Properties properties, Class<T> policyClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T t = (T) policyClass.getDeclaredConstructor().newInstance().loadFromProperties(properties);
        return t;
    }
}
