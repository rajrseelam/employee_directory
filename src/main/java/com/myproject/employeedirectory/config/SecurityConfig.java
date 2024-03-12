package com.myproject.employeedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Other configurations...
                .authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/h2-console/**").permitAll())// Allow
                                                                                                                        // H2
                                                                                                                        // console
                                                                                                                        // access
                .csrf(csrf -> csrf.disable())
                // Allow use of frameOptions for the H2 console
                .headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }

}
