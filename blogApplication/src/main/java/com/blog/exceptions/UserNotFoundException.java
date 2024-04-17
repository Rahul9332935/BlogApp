package com.blog.exceptions;

public class UserNotFoundException extends BlogException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public UserNotFoundException(String message) {
		super(message);
	}
}
