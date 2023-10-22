package com.warehouse.exception;

public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2514084794474059820L;

	public UsernameNotFoundException() {

	}

	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

}
