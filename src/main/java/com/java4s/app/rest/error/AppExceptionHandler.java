package com.java4s.app.rest.error;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {	
	
	@ResponseBody
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<?> handleException(ValidationException exception) {
		
		System.out.println("Comes to handleException of AppExceptionHandler");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

}