package com.bk.authservice.auth.handler.x509;

import com.bk.authservice.auth.policy.Policy;

import java.util.Properties;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/02/22
 */
public class X509Policy implements Policy {
    private String issuer;
    private static final String PREFIX="x509.";

    public X509Policy() {
    }

    @Override
    public Policy loadFromProperties(Properties properties) {
        this.issuer = properties.getProperty(PREFIX + "issuer");
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
