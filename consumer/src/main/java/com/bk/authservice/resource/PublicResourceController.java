package com.bk.authservice.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All the endpoints in this controller are public. No Authentication is needed.
 */
@RestController("/public")
public class PublicResourceController {
    @GetMapping(value="/hello")
    public String helloWorld(HttpServletRequest request, HttpServletResponse response) {
        return "<h1>Public Hello World</h1>";
    }
}
