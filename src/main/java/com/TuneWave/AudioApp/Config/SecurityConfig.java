package com.TuneWave.AudioApp.Config;

import com.TuneWave.AudioApp.User.MyUserDetailService;
import com.TuneWave.AudioApp.User.User;
import com.TuneWave.AudioApp.User.UserPrincipal;
import com.TuneWave.AudioApp.User.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailService userDetailService;

    public SecurityConfig(MyUserDetailService userDetailService){
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disable Cross-Site Request Forgery (CSRF) protection.
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        // Authenticate all the request
        .authorizeHttpRequests(request -> {
            request.requestMatchers("/users/register").permitAll();
            request.anyRequest().authenticated();
        })
        // way to enable HTTP Basic Authentication using all the default settings provided by Spring Security
        .httpBasic(Customizer.withDefaults())
        // Making http request stateless. So new session will be created for each request.
        .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return  httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException, NoSuchProviderException {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Passing password encoder to provider
        provider.setPasswordEncoder(User.getPasswordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

}
