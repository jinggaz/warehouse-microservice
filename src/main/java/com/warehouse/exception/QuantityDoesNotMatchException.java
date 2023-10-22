package com.warehouse.exception;

public class QuantityDoesNotMatchException extends RuntimeException {

	private static final long serialVersionUID = -687579129170578565L;

	public QuantityDoesNotMatchException() {

	}

	public QuantityDoesNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuantityDoesNotMatchException(String message) {
		super(message);
	}

}
