package com.springsecurity.config;

import com.springsecurity.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder()  throws  Exception{
        return new BCryptPasswordEncoder();
    }

//    Createing users for authorization
    @Bean
    public UserDetailsService userDetailsService()  throws  Exception{
//        UserDetails admin = User.withUsername("aarrunyal")
//                .password(passwordEncoder().encode("aarrunyal"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("aarrunyall")
//                .password(passwordEncoder().encode("aarrunyal"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        return http
                .csrf().disable().
                authorizeHttpRequests().requestMatchers("/api/greetings/hello").permitAll().
                and().
                authorizeHttpRequests().requestMatchers("/api/greetings/**").authenticated().
                and().formLogin().and().build();
    }
}
