package com.bk.authservice.handler.x509;

import com.bk.authservice.handler.AuthenticationHandler;
import com.bk.authservice.handler.AuthenticationType;
import com.bk.authservice.util.Constants;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/02/22
 */
public class X509AuthenticationHandler implements AuthenticationHandler<X509Credentials, X509Policy> {
    @Override
    public Map authenticate(X509Credentials credentials, X509Policy policy) throws Exception {
        X509Certificate[] certificateChain = credentials.getCertificateChain();
        boolean isValidCertificate = certificateChain[0].getIssuerDN().getName().equals(policy.getIssuer());
        // TODO - validate the chain or even a special attribute inside the certificate can be checked
        // TODO - verify notAfter and notBefore
        if(!isValidCertificate) {
            // auth failure
            throw new Exception("Authentication failure! Certificate is invalid.");
        }

        Map<String, String> attributes = new HashMap<>();
        attributes.put(Constants.USERNAME, certificateChain[0].getSubjectDN().getName());
        return attributes;
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return AuthenticationType.X509;
    }
}
