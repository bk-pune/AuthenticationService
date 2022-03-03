package com.bk.authservice.handler.oidc;

import com.bk.authservice.handler.AuthenticationHandler;
import com.bk.authservice.handler.AuthenticationType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.okta.jwt.IdTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerifiers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class OIDCAuthenticationHandler implements AuthenticationHandler<OIDCCredentials, OIDCPolicy> {

    @Override
    public Map authenticate(OIDCCredentials credentials, OIDCPolicy oidcPolicy) throws Exception {
        // exchange the authorization code with server
        // in return, get the user details
        HttpRequest httpRequest = prepareAuthCodeExchangeRequest(credentials, oidcPolicy);
        String rawResponse = httpRequest.execute().parseAsString();

        // Step 3 - Validate id token
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(rawResponse);
        String id_token = jsonResponse.get("id_token").textValue();


        IdTokenVerifier idTokenVerifier = JwtVerifiers.idTokenVerifierBuilder()
                .setIssuer(oidcPolicy.getIssuer())
                .setReadTimeout(Duration.ofSeconds(180))
                .setClientId(oidcPolicy.getClientId())
                .build();
        Jwt jwtToken = idTokenVerifier.decode(id_token, null);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("JWT", jwtToken);
        return attributes;
    }

    private HttpRequest prepareAuthCodeExchangeRequest(OIDCCredentials credentials, OIDCPolicy oidcPolicy) throws IOException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", GrantType.AUTHORIZATION_CODE.value);
        formData.add("redirect_uri", oidcPolicy.getAuthorizationCodeCallbackEndpoint());
        formData.add("code", credentials.getCode());
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(oidcPolicy.getTokenEndpoint()), new UrlEncodedContent(formData));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAuthorization("Basic " + Base64.getEncoder().encodeToString((credentials.getClientId() + ":" + credentials.getClientSecret()).getBytes(StandardCharsets.UTF_8)));
        request.setHeaders(httpHeaders); //.header(HttpHeaders.AUTHORIZATION, "Basic " + new String(oidcPolicy.getClientId() + ":" + oidcPolicy.getClientSecret()))
        return request;
    }

    public enum GrantType {
        AUTHORIZATION_CODE("authorization_code"),
        REFRESH_TOKEN("refresh_token");

        private String value;
        GrantType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return AuthenticationType.OIDC;
    }
}
