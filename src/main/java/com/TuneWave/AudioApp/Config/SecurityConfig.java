package com.TuneWave.AudioApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disable spring security default thing
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        // Authenticate all the request
        .authorizeHttpRequests(request -> request.anyRequest().authenticated())
        // way to enable HTTP Basic Authentication using all the default settings provided by Spring Security
        .httpBasic(Customizer.withDefaults())
        // Making http request stateless. So new session will be created for each request.
        .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return  httpSecurity.build();
    }

}
