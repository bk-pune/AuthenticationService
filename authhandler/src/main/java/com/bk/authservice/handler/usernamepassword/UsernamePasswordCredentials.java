package com.bk.authservice.handler.usernamepassword;

import com.bk.authservice.handler.Credentials;

public class UsernamePasswordCredentials implements Credentials {
    private String username;
    private String password;

    public UsernamePasswordCredentials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
