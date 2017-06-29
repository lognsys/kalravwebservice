package com.lognsys.exception;

@SuppressWarnings("serial")
public class DramaDataAccessException extends RuntimeException {

	public DramaDataAccessException() {
		super();
	}

	public DramaDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DramaDataAccessException(String message) {
		super(message);
	}

	public DramaDataAccessException(Throwable cause) {
		super(cause);
	}

}
