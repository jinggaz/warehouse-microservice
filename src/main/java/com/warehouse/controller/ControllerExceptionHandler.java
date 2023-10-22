package com.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.warehouse.exception.QuantityDoesNotMatchException;
import com.warehouse.exception.ReceiveNotFoundException;
import com.warehouse.exception.UsernameNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { ReceiveNotFoundException.class })
	public ResponseEntity<String> receiveNotFoundException(ReceiveNotFoundException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
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
