package com.app.exceptions;

public class InvalidLoginException extends Exception{

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(final String message) {
		super(message);
	}

}
