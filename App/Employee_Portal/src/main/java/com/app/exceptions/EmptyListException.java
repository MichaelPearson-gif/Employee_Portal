package com.app.exceptions;

public class EmptyListException extends Exception{

	public EmptyListException() {
		super();
	}

	public EmptyListException(final String message) {
		super(message);
	}
	
}
