package com.masai.exception;

public class SomethingWentWrongException extends Exception {
	
	public SomethingWentWrongException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
}
