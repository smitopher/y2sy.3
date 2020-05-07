package com.cmfirsttech.y2.api.mapper;

public class MappingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MappingException(String message, Throwable e) {
		super(message, e);
	}
	
	public MappingException(String message) {
		super(message);
	}

}
