package com.example.demotaco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity hamburglar) throws Exception {
        hamburglar
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("permitAll");
             /*   .access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll")
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll() */
    }
}
