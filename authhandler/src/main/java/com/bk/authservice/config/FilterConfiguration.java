package com.bk.authservice.config;

import com.bk.authservice.filters.TicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FilterConfiguration {
    @Autowired
    private TicketValidationFilter ticketValidationFilter;

    @Bean
    public FilterRegistrationBean<TicketValidationFilter> ticketValidationFilterRegistration() {
        FilterRegistrationBean<TicketValidationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(ticketValidationFilter);
        registration.addUrlPatterns("/*");
        registration.setName("ticketValidationFilter");
        registration.setOrder(1);
        return registration;
    }
}
