package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(code=HttpStatus.CONFLICT, reason="Company Code is already exists in database")
public class CompanyCodeAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
