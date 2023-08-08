package com.example.july2023.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); //- Directly linking user USERS database table to Spring Security
        //USERS table - id, username, password - USER
        //AUTHORITIES - id, username, authority - ROLE

        //JWT - Java Web Token - Token based authorization
        /*
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("user")).roles("USER").and()
                .withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN", "SECURITY","NETWORK").and()
                .withUser("vamshi").password(encoder.encode("password")).roles("ADMIN").and()
                .withUser("kiran").password(encoder.encode("password")).roles("SECURITY").and()
                .withUser("mahesh").password(encoder.encode("password")).roles("NETWORK").and()
                .withUser("firstuser").password(encoder.encode("testpassword")).roles("USER","SECURITY");*/
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/student/get/**").hasAuthority("ROLE_USER")//SECURED - ACCESSED BY ALL USERS with USER ROLE
//                .antMatchers("/student/all").hasAnyRole("NETWORK", "ADMIN")//NO SECURITY - PERMITTED TO EVERYONE
//                .antMatchers("/student/add").hasRole("ADMIN")//Only users with ADMIN role can access
//                .antMatchers("/courses/**").hasRole("SECURITY")//Only users with ADMIN role can access
//                .antMatchers("/calculate/**").permitAll()
                .and()
                .httpBasic();
    }
}
