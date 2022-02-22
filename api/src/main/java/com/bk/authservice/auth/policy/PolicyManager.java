package com.bk.authservice.auth.policy;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/02/22
 */
public interface PolicyManager {
    <T extends Policy> T getPolicy(Class<T> policyClass);
    <T extends Policy> void registerPolicy(Class<T> policyClass, T policy);
    <T extends Policy> T preparePolicyFromProperties(Properties properties, Class<T> policyClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
