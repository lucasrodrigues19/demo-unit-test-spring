package com.lucasrodrigues.tubi.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@SuperBuilder
@Data
public class ValidationExceptionDetails extends MainExceptionDetails {

	private final String fields;
	
	private final String fieldMessages;
}
