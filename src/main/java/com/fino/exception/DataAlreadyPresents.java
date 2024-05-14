package com.fino.exception;

public class DataAlreadyPresents extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataAlreadyPresents() {
		super();
	}
	public DataAlreadyPresents(String message) {
		super(message);
	}
}
