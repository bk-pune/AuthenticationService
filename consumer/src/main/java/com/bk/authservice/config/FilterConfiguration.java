package com.bk.authservice.config;

import com.bk.authservice.filters.TicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<TicketValidationFilter> ticketValidationFilterRegistration() {
        FilterRegistrationBean<TicketValidationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(getTicketValidationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("ticketValidationFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public TicketValidationFilter getTicketValidationFilter() {
        return new TicketValidationFilter();
    }
}
