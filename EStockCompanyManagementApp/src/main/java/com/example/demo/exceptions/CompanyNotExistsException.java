package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code=HttpStatus.CONFLICT, reason="The Company is not exists..!!")
@ControllerAdvice
public class CompanyNotExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
