package com.bk.authservice.controller;

import com.bk.authservice.strategy.AuthenticationStrategy;
import com.bk.authservice.strategy.AuthenticationStrategyResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/usernamepassword/*")
public class UsernamePasswordAuthenticationController implements AuthenticationController {

    @Autowired
    private AuthenticationStrategyResolverImpl authenticationStrategyResolver;

    @GetMapping(value = "/login.html")
    @ResponseBody
    public String showLoginForm() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "body {font-family: Arial, Helvetica, sans-serif;}\n" +
                "form {border: 3px solid #f1f1f1;}\n" +
                "\n" +
                "input[type=text], input[type=password] {\n" +
                "  width: 100%;\n" +
                "  padding: 12px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  display: inline-block;\n" +
                "  border: 1px solid #ccc;\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "button {\n" +
                "  background-color: #04AA6D;\n" +
                "  color: white;\n" +
                "  padding: 14px 20px;\n" +
                "  margin: 8px 0;\n" +
                "  border: none;\n" +
                "  cursor: pointer;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "button:hover {\n" +
                "  opacity: 0.8;\n" +
                "}\n" +
                "\n" +
                ".imgcontainer {\n" +
                "  text-align: center;\n" +
                "  margin: 24px 0 12px 0;\n" +
                "}\n" +
                "\n" +
                "\n" +
                ".container {\n" +
                "  margin: auto;\n" +
                "  width: 30%;\n" +
                "  border: none;\n" +
                "  padding: 10px;\n" +
                "}\n" +
                "\n" +
                "span.psw {\n" +
                "  float: right;\n" +
                "  padding-top: 16px;\n" +
                "}\n" +
                "\n" +
                "/* Change styles for span and cancel button on extra small screens */\n" +
                "@media screen and (max-width: 300px) {\n" +
                "  span.psw {\n" +
                "     display: block;\n" +
                "     float: none;\n" +
                "  }\n" +
                "  .cancelbtn {\n" +
                "     width: 100%;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<form action=\"/auth/usernamepassword/login\" method=\"post\">\n" +
                "\n" +
                "  <div class=\"container\">\n" +
                "  <h2>Login Form</h2>\n" +
                "    <label for=\"uname\"><b>Username</b></label>\n" +
                "    <input type=\"text\" placeholder=\"Enter Username\" name=\"username\" required>\n" +
                "\n" +
                "    <label for=\"psw\"><b>Password</b></label>\n" +
                "    <input type=\"password\" placeholder=\"Enter Password\" name=\"password\" required>\n" +
                "        \n" +
                "    <button type=\"submit\">Login</button>\n" +
                "  </div>\n" +
                "\n" +
                "\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    @PostMapping(value = "/login")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        AuthenticationStrategy authenticationStrategy = authenticationStrategyResolver.resolveAuthenticationStrategy();
        authenticationStrategy.handleRequest(httpServletRequest, httpServletResponse);
    }
}