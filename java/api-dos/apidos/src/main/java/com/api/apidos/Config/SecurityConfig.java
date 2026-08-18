package com.api.apidos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
@Configuration
public class SecurityConfig {
     @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api-dos/**").authenticated()
                .anyRequest().authenticated()
            )

            .oauth2ResourceServer(oauth ->
                oauth.jwt(Customizer.withDefaults())
            );

        // Para permitir visualizar H2 Console
        http.headers(headers ->
            headers.frameOptions(frame -> frame.sameOrigin())
        );

        return http.build();
    }
}
