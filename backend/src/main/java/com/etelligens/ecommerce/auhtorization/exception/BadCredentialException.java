package com.etelligens.ecommerce.auhtorization.exception;

public class BadCredentialException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555413765303144922L;
	
	public BadCredentialException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
