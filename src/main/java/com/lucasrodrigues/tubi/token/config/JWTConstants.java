package com.lucasrodrigues.tubi.token.config;

/**
 * @author Lucas Rodrigues
 * @since  2022/02/19
 * authentication with jwt, step 02
 */
public class JWTConstants {
	public static final String HEADER_TOKEN = "Authorization";
	public static final String PARAMETER_TOKEN = "token";
	public static final String PREFIX_TOKEN = "Bearer";
	public static final String SECRET_KEY = "Teste";
	public static final int SECONDS_EXPIRE = 3600000;
}
