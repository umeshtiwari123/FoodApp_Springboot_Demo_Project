package com.ty.foodapp.foodapp_boot.exception;

public class UnableToDeleteException extends RuntimeException {

	private String message = "No Such Id Found In The Database To Be Deleted";

	public String toMessage() {
		return message;

	}

	public UnableToDeleteException(String message) {
		super();
		this.message = message;
	}

	public UnableToDeleteException() {
		super();
	}

}
