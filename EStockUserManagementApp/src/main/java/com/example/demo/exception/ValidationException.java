package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED, reason="Please provide correct credentials")
public class ValidationException extends Exception{

	public ValidationException(String string) {
		// TODO Auto-generated constructor stub
	}

}
