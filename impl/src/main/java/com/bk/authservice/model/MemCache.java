package com.bk.authservice.model;

import java.util.HashMap;

/**
 * Maintains cookie cache - TODO remove it and replace with DAO
 */
public class MemCache {
    private static final HashMap<String, RequestData> preAuthCookieCache = new HashMap<>();
    private static final HashMap<String, String> tokenCache = new HashMap<>();

    public static synchronized void putRequestData(String requestId, RequestData requestData){
        preAuthCookieCache.put(requestId, requestData);
    }

    public static synchronized RequestData getRequestData(String requestId){
        return preAuthCookieCache.get(requestId);
    }

    public static synchronized RequestData removeRequestData(String requestId) {
        return preAuthCookieCache.remove(requestId);
    }

    public static synchronized void putToken(String tokenId, String tokenValue){
         tokenCache.put(tokenId, tokenValue);
    }

    public static synchronized void removeToken(String tokenId) {
        tokenCache.remove(tokenId);
    }

    public synchronized String getToken(String tokenId) {
        return tokenCache.get(tokenId);
    }
}
