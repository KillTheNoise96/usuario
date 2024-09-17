//package com.api.usuario.config;
//
//import com.api.usuario.config.jwt.JwtFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        FilterRegistrationBean filter = new FilterRegistrationBean();
//        filter.setFilter(new JwtFilter());
//        filter.addUrlPatterns("/api/users/all");
//        return filter;
//    }
//}