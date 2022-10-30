package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CompanyExceptionHandler extends ResponseEntityExceptionHandler{
	
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions(MethodArgumentNotValidException ex){
	 * Map<String, String> errors = new HashMap<String, String>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError)error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put("Name", fieldName);
	 * errors.put("Message", errorMessage); }); return errors;
	 * 
	 * }
	 */
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put("FieldName", fieldName);
			errors.put("Message", errorMessage);
		});
	
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

}
