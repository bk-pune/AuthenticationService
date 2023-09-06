package com.bk.authservice.resource;

import com.bk.authservice.policy.Policy;
import com.bk.authservice.policy.PolicyManager;
import com.bk.authservice.util.CookieUtils;
import com.bk.authservice.policy.GlobalPolicy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrivateResourceController {

    @Autowired
    private PolicyManager policyManager;

    @GetMapping(value="/hello")
    public String helloWorld(HttpServletRequest request, HttpServletResponse response) {
        return "<h1>Hello World</h1>";
    }

    @GetMapping(value="/")
    public String defaultMethod(HttpServletRequest request, HttpServletResponse response) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "body {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  margin: 0;\n" +
                "}\n" +
                "\n" +
                "html {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "*, *:before, *:after {\n" +
                "  box-sizing: inherit;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                ".card {\n" +
                "  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);\n" +
                "  margin: 8px;\n" +
                "}\n" +
                "\n" +
                ".about-section {\n" +
                "  padding: 50px;\n" +
                "  text-align: center;\n" +
                "  background-color: #474e5d;\n" +
                "  color: white;\n" +
                "}\n" +
                ".hello-section {\n" +
                "  padding: 50px;\n" +
                "  text-align: center;\n" +
                "  background-color: #5f7877;\n" +
                "  color: white;\n" +
                "  height:10%;\n" +
                "}\n" +
                ".container {\n" +
                "  padding: 0 16px;\n" +
                "}\n" +
                "\n" +
                ".container::after, .row::after {\n" +
                "  content: \"\";\n" +
                "  clear: both;\n" +
                "  display: table;\n" +
                "}\n" +
                "\n" +
                ".title {\n" +
                "  color: grey;\n" +
                "}\n" +
                "\n" +
                ".button {\n" +
                "  border: none;\n" +
                "  outline: 0;\n" +
                "  display: inline-block;\n" +
                "  padding: 8px;\n" +
                "  color: white;\n" +
                "  background-color: #000;\n" +
                "  text-align: center;\n" +
                "  cursor: pointer;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                ".button:hover {\n" +
                "  background-color: #555;\n" +
                "}\n" +
                "\n" +
                "@media screen and (max-width: 650px) {\n" +
                "  .column {\n" +
                "    width: 100%;\n" +
                "    display: block;\n" +
                "  }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"hello-section\">\n" +
                "<h2 style=\"text-align:center\">Principal: " + CookieUtils.extractCookie(request, CookieUtils.TOKEN_COOKIE_NAME).getValue().split("_")[1]+"<br><br>\n" +
                "Authentication Handler: " + policyManager.getPolicy(GlobalPolicy.class).getAuthenticationHandler().getHandlerName() +"</h2>"+
                "</div>\n" +
                "<div class=\"about-section\">\n" +
                "  <h1>YAAS</h1>\n" +
                "  <p><h2>Yet Another Authentication Service</h2></p>\n" +
                "  <p><h3>It allows software products to single sign-on with external Identity Providers, and provide secure access to the local resources.</h3></p>\n" +
                "  <p>Under development</p>\n" +
                "</div>\n" +
                "<div class=\"navigation\">\n" +
                "<a class=\"button\" href=\"/logout\">\n" +
                "<div class=\"logout\">LOGOUT</div>\n" +
                "</a>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
                /*"<h1>Authentication Service - Default Landing Page !</h1>"
                + "<br>"
                + "Authentication Handler: " + policyManager.getPolicy(GlobalPolicy.class).getAuthenticationHandler().getHandlerName() + "<br>"
               */
    }

    @GetMapping(value="/policy/{policyType}")
    @ResponseBody
    public <T extends Policy> T getPolicy(@PathVariable String policyType) throws ClassNotFoundException {
        return (T) policyManager.getPolicy(GlobalPolicy.class); //TODO
    }
}
