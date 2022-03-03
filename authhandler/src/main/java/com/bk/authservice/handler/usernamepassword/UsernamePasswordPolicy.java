package com.bk.authservice.handler.usernamepassword;

import com.bk.authservice.policy.Policy;

import java.util.Properties;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 17/02/22
 */
public class UsernamePasswordPolicy implements Policy {
    @Override
    public Policy loadFromProperties(Properties properties) {
        return this;
    }
}
