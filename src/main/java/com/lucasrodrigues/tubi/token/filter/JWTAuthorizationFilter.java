package com.lucasrodrigues.tubi.token.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.lucasrodrigues.tubi.entitys.User;
import com.lucasrodrigues.tubi.token.config.JWTConstants;

import io.jsonwebtoken.Jwts;

/**
 * @author Lucas Rodrigues
 * @since  2022/02/19
 * authentication with jwt, step 04
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsServiceImpl;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsServiceImpl) {
		super(authenticationManager);
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		String token = getToken(request);
		if(!StringUtils.hasLength(token)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) throws IOException, ServletException {
		String token = null;
		if(request.getHeader(JWTConstants.HEADER_TOKEN) != null) {
			token = request.getHeader(JWTConstants.HEADER_TOKEN);
			if(!token.startsWith(JWTConstants.PREFIX_TOKEN)) 
				return null;
		}else if(request.getParameter(JWTConstants.PARAMETER_TOKEN) != null)	
			token = request.getHeader(JWTConstants.PARAMETER_TOKEN);
		
		return token;
	}

	public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		String username = Jwts.parser()
				.setSigningKey(JWTConstants.SECRET_KEY)
				.parseClaimsJws(token.replace(JWTConstants.PREFIX_TOKEN, ""))
				.getBody()
				.getSubject();
		User user = (User) userDetailsServiceImpl.loadUserByUsername(username);
		
		return new UsernamePasswordAuthenticationToken(username,null, user.getAuthorities());
	}
	
}
