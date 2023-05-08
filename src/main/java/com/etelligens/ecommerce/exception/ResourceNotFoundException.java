package com.etelligens.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5332909058634923777L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
