package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
@ResponseStatus(code=HttpStatus.CONFLICT, reason="Company Code is already exists in database")
public class CompanyCodeAlreadyExistsException extends Exception{

}
