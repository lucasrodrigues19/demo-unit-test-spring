package com.lucasrodrigues.tubi.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lucasrodrigues.tubi.services.UserDetailsServiceImpl;
import com.lucasrodrigues.tubi.token.security.TokenSecurityConfigAdapter;
import com.lucasrodrigues.tubi.utils.PasswordEncoderUtil;

import lombok.RequiredArgsConstructor;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@EnableWebSecurity
public class TubiSecurity extends TokenSecurityConfigAdapter {
	
	private  UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	public TubiSecurity(UserDetailsServiceImpl userDetailsServiceImpl) {
		super("/login/auth/v1");
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.headers().frameOptions().disable().and()
//		.authorizeRequests() 
//		.antMatchers("/anime/admin/**").hasRole("ADMIN")
//		.antMatchers("/anime/**").hasRole("USER")
//		.antMatchers(publicMatchers()).permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin()
//		.and()
//		.httpBasic();
//	
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/anime/admin/**").hasRole("ADMIN")
		.antMatchers("/anime/**").hasRole("USER")
		.antMatchers(publicMatchers()).permitAll()
		.anyRequest()
		.authenticated();
		super.configure(http);
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderUtil.getInstance();
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
	}

	private String[] publicMatchers () {
		String[] matchers = {"/h2-console/**"};
		return matchers;
	}

	//authentication with jwt, step 06
	@Override
	public UserDetailsService userDetailsService() {
		return this.userDetailsServiceImpl;
	}

	
	
}
