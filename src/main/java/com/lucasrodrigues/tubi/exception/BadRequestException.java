package com.lucasrodrigues.tubi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	
}
