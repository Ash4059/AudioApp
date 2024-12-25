package com.TuneWave.AudioApp.Config;

import com.TuneWave.AudioApp.Service.Implementation.MyUserDetailService;
import com.TuneWave.AudioApp.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailService userDetailService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(MyUserDetailService userDetailService, JwtFilter jwtFilter){
        this.userDetailService = userDetailService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disable Cross-Site Request Forgery (CSRF) protection.
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        // Authenticate all the request
        .authorizeHttpRequests(request -> {
            request.requestMatchers("/users/register", "/users/login").permitAll();
            request.anyRequest().authenticated();
        })
        // way to enable HTTP Basic Authentication using all the default settings provided by Spring Security
        .httpBasic(Customizer.withDefaults())
        // Making http request stateless. So new session will be created for each request.
        .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:1234")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException, NoSuchProviderException {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Passing password encoder to provider
        provider.setPasswordEncoder(User.getPasswordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
