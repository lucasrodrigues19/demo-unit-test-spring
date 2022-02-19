package com.lucasrodrigues.tubi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
public class PasswordEncoderUtil {

	public static PasswordEncoder getInstance() {
		return new BCryptPasswordEncoder();
	}
}
