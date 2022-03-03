package com.bk.authservice.policy;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * A centralized place where all the policies are managed.<br>
 * How the policies to be registered, retrieved and maintained is implementation specific.<br><br>
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 16/02/22
 */
public interface PolicyManager {
    /**
     * Get the policy specified by the Policy Class
     * @param policyClass Class of the Policy to be returned
     * @param <T> Policy Type
     * @return Specific Policy of Type passed
     */
    <T extends Policy> T getPolicy(Class<T> policyClass);

    /**
     * Register the given policy. <br>How it is stored/maintained is implementation specific.
     * @param policyClass
     * @param policy
     * @param <T>
     */
    <T extends Policy> void registerPolicy(Class<T> policyClass, T policy);

    /**
     * A utility method which creates a specific policy instance from the given set of properties.
     * @param properties
     * @param policyClass
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    <T extends Policy> T preparePolicyFromProperties(Properties properties, Class<T> policyClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
