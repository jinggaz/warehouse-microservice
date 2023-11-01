package com.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.warehouse.exception.QuantityDoesNotMatchException;
import com.warehouse.exception.UsernameNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<String> receiveNotFoundException(ConstraintViolationException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { QuantityDoesNotMatchException.class })
	public ResponseEntity<String> quantityDoesNotMatchException(QuantityDoesNotMatchException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { UsernameNotFoundException.class })
	public ResponseEntity<String> usernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

}
