package com.lucasrodrigues.tubi.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lucasrodrigues.tubi.utils.PasswordEncoderUtil;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class TubiSecurity extends WebSecurityConfigurerAdapter {@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests() 
		.antMatchers("/anime/admin/**").hasRole("ADMIN")
		.antMatchers("/anime/**").hasRole("USER")
		.antMatchers(publicMatchers()).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderUtil.getInstance();
		auth.userDetailsService(null).passwordEncoder(passwordEncoder);
	}

	private String[] publicMatchers () {
		String[] matchers = {"/h2-console/**"};
		return matchers;
	}

	
	
}
