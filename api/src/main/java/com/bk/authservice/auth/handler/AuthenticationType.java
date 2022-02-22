package com.bk.authservice.auth.handler;

public enum AuthenticationType {
    USERNAME_PASSWORD("USERNAME_PASSWORD"),
    OIDC("OIDC");

    private String handlerName;
    private AuthenticationType(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public static AuthenticationType getHandler(String handlerName) {
        AuthenticationType[] values = AuthenticationType.values();
        for(AuthenticationType handler: values) {
            if(handler.getHandlerName().equals(handlerName)) {
                return handler;
            }
        }
        return null;
    }
}
