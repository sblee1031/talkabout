package com.talkabout.exception;

public class AddException extends Exception {

	public AddException(String message) {
		super(message);
	}
	
	public void DuplicatedException(String message) {
		System.out.println("PK 중복");
	}
}
