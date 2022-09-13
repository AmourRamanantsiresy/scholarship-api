package com.web.scholarship.security;

import com.web.scholarship.services.dbUser.DBUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConf extends WebSecurityConfigurerAdapter {
    private DBUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.PUT, "/user").permitAll()
                .antMatchers("/whoami").authenticated()
                .antMatchers(HttpMethod.GET, "/country/**").authenticated()
                .antMatchers(HttpMethod.GET, "/study-level/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/candidate").authenticated()
                .antMatchers(HttpMethod.GET, "/application/candidate/{id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/application/apply").authenticated()
                .anyRequest().hasRole("admin")
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean
    protected PasswordEncoder getPass() {
        return new BCryptPasswordEncoder();
    }
}

