package com.ty.foodapp.foodapp_boot.exception;

public class UnableToUpdateException extends RuntimeException {
	private String message = "No Such Id Found In The Database To Be Updated";

	public String toMessage() {
		return message;

	}

	public UnableToUpdateException(String message) {
		this.message = message;
	}

	public UnableToUpdateException() {
		super();
	}

}
