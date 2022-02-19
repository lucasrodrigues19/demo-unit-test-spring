package com.lucasrodrigues.tubi.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucasrodrigues.tubi.exception.BadRequestException;
import com.lucasrodrigues.tubi.exception.BadRequestExceptionDetails;
import com.lucasrodrigues.tubi.exception.ValidationExceptionDetails;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@ControllerAdvice
public class RestExceptionHandler {

	
	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException ex){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(BadRequestExceptionDetails
						.builder()
						.title("Bad Request Exception")
						.details(ex.getMessage())
						.developerMessage(ex.getClass().getName())
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.build()
				);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<FieldError> listFieldErros = ex.getBindingResult().getFieldErrors();
		String fields = listFieldErros.stream().map(e -> e.getField()).collect(Collectors.joining(", "));
		String fieldMesages = listFieldErros.stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ValidationExceptionDetails
						.builder()
						.title("Bad Request Exception, Invalid Fields")
						.details("Check Fields Errors")
						.developerMessage(ex.getClass().getName())
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.fieldMessages(fieldMesages)
						.fields(fields)
						.build()
				);
	}
}
