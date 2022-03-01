package com.bk.authservice.auth.handler.x509;

import com.bk.authservice.auth.handler.Credentials;

import java.security.cert.X509Certificate;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 23/02/22
 */
public class X509Credentials implements Credentials {
    X509Certificate [] certificateChain;

    public X509Certificate[] getCertificateChain() {
        return certificateChain;
    }

    public void setCertificateChain(X509Certificate[] certificateChain) {
        this.certificateChain = certificateChain;
    }
}
