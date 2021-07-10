package com.talkabout.exception;

public class AddException extends Exception {
<<<<<<< HEAD
	public AddException(String message ) {
		super(message);
	}

=======

	public AddException(String message) {
		super(message);
	}
	
	public void DuplicatedException(String message) {
		System.out.println("PK 중복");
	}
>>>>>>> 173f792aa85f8cdae498f11b5e4a8ac11d9cb0e8
}
