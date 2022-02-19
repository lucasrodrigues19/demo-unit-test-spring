package com.lucasrodrigues.tubi.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@SuperBuilder
@Data
public class MainExceptionDetails {

	protected String title;
	
	protected int status;
	
	protected String details;
	
	protected String developerMessage;
	
	protected LocalDateTime timestamp;
}
