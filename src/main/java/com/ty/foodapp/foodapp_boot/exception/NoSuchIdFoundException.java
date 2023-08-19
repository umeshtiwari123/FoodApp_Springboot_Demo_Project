package com.ty.foodapp.foodapp_boot.exception;

public class NoSuchIdFoundException extends RuntimeException {

	private String message = "No Such Id Found In The Database";

	public String toMessage() {
		return message;

	}

	public NoSuchIdFoundException(String message) {
		this.message = message;
	}

	public NoSuchIdFoundException() {
	
	}

}
