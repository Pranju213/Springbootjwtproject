package com.springbootjwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.springbootjwt.filter.JwtTokenFilter;

/*
 SecurityFilterChain bean, 
 define a lot of configurations such as the
 session creation policy as stateless, and also
 disable cors and csrf. Also, add configuration to 
 expose the UserController API endpoint as public. 
 And secured other API endpoints
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain 
    		filterChain(HttpSecurity httpSecurity) 
            	throws Exception 
     {

        httpSecurity = httpSecurity.cors()
        				.and().csrf().disable();
        httpSecurity=httpSecurity.sessionManagement()
        			.sessionCreationPolicy
                    (SessionCreationPolicy.STATELESS)
                    .and();

        httpSecurity.authorizeRequests()
        		.antMatchers(HttpMethod.POST,"/user/**")
                .permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore
        	(jwtTokenFilter, 
            UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}

