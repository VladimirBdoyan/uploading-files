package com.example.fileuploader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/files/upload/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Client()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/");
    }
}
