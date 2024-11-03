package com.car_shop.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.car_shop.handlers.AuthEntryPoint;
import com.car_shop.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refresh-token";

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpRequest) throws Exception {
        httpRequest.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN)
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpRequest.build();
    }
}
