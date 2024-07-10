package com.example.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MysteriousConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        xMethod(http);
    }

    private void xMethod(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/secure/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .httpBasic();
    }

    @Bean
    public UserDetailsService yService() {
        return new InMemoryUserDetailsManager(
            org.springframework.security.core.userdetails.User.withUsername("user")
                .password(zMethod("password"))
                .roles("USER")
                .build()
        );
    }

    private String zMethod(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
