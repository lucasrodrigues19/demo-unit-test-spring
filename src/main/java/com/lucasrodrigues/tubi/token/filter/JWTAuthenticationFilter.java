package com.lucasrodrigues.tubi.token.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasrodrigues.tubi.entitys.User;
import com.lucasrodrigues.tubi.token.config.JWTConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Lucas Rodrigues
 * @since  2022/02/19
 * authentication with jwt, step 03
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String filterProcessesUrl) {
		super();
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(filterProcessesUrl);
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(),User.class);
		    return this.authenticationManager
		    		   .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException   e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		String token = Jwts.builder()
				       .setSubject(user.getUsername())
				       .setExpiration(new Date(System.currentTimeMillis() + JWTConstants.SECONDS_EXPIRE))
				       .signWith(SignatureAlgorithm.HS512, JWTConstants.SECRET_KEY)
				       .compact();
		
		response.setHeader(JWTConstants.HEADER_TOKEN, JWTConstants.PREFIX_TOKEN+" "+token);
	}
	


}
