package com.lucasrodrigues.tubi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

	public static PasswordEncoder getInstance() {
		return new BCryptPasswordEncoder();
	}
}
