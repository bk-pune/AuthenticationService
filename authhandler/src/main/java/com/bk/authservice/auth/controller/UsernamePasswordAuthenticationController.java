package com.bk.authservice.auth.controller;

import com.bk.authservice.auth.strategy.AuthenticationStrategy;
import com.bk.authservice.auth.strategy.AuthenticationStrategyResolverImpl;
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
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Spring Login Form</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form name=\"submitForm\" method=\"POST\" action=\"/auth/usernamepassword/login\">\n" +
                "    <div align=\"center\">\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td>User Name</td>\n" +
                "                <td><input type=\"text\" name=\"username\" /></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Password</td>\n" +
                "                <td><input type=\"password\" name=\"password\" /></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td></td>\n" +
                "                <td><input type=\"submit\" value=\"Submit\" /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "    </div>\n" +
                "</form>\n" +
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