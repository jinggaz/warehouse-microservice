package com.warehouse.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6627650208979220230L;

	public ProductNotFoundException() {

	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

}
