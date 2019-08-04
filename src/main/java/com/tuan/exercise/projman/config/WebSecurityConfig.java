package com.tuan.exercise.projman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String ROLE_USER = AccountInfo.getRoleMap().get("USR");
    private static final String ROLE_ADMIN = AccountInfo.getRoleMap().get("ADM");

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET ,"/api/release/list", "/api/service/list", "/api/service/list-version").hasRole(ROLE_USER)
            .antMatchers(HttpMethod.GET, "/api/release/delete").hasRole(ROLE_ADMIN)
            .antMatchers(HttpMethod.POST ,"/api/release/create", "/api/release/update", "/api/service/create").hasRole(ROLE_ADMIN)
            .and()
            .csrf().disable()
            .formLogin().disable();
    }
}
