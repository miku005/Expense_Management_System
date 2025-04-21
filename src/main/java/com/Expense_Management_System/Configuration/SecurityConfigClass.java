package com.Expense_Management_System.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfigClass {

    private JwtFilter jwtFilter;

    public SecurityConfigClass(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests().anyRequest().permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/user/sign-in","/api/user/login","/api/user/expense/sign-in")
                .permitAll()
                .requestMatchers("/api/expense/addExpense")
                .hasRole("MANAGER")
                .requestMatchers("/api/expense/addExpense","/api/expense/deleteExpense","/api/expense/updateExpense")
                .hasAnyRole("MANAGER","ADMIN")
                .anyRequest().authenticated();
        return http.build();
    }
}
