package com.car_shop.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.car_shop.enums.MessageType;
import com.car_shop.exceptions.BaseException;
import com.car_shop.exceptions.ErrorMessage;
import com.car_shop.models.User;
import com.car_shop.repositories.UserRepository;

@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> optional = userRepository.findByUsername(username);

                if (optional.isEmpty()) {
                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
                }
                return optional.get();
            }

        };
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
