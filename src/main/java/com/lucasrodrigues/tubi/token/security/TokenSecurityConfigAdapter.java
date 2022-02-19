package com.lucasrodrigues.tubi.token.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lucasrodrigues.tubi.token.filter.JWTAuthenticationFilter;
import com.lucasrodrigues.tubi.token.filter.JWTAuthorizationFilter;

/**
 * @author Lucas Rodrigues
 * @since  2022/02/19
 * authentication with jwt, step 05
 */
public abstract class TokenSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	private String filterProcessesUrl;
	
	public TokenSecurityConfigAdapter(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.headers().frameOptions().disable()
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.exceptionHandling().authenticationEntryPoint((req,resp,e)->resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
		.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(),filterProcessesUrl))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
	}
	
	@Bean
	public abstract UserDetailsService userDetailsService();
}
