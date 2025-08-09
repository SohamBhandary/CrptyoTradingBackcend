package com.soham.TradingPlatform.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)  throws Exception{
        httpSecurity.sessionManagement(managment->managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/**").authenticated().anyRequest().permitAll());

        return null;
    }
}
