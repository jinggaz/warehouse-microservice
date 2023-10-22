package com.warehouse.exception;

public class ReceiveNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8794960900740969209L;

	public ReceiveNotFoundException() {

	}

	public ReceiveNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReceiveNotFoundException(String message) {
		super(message);
	}

}
