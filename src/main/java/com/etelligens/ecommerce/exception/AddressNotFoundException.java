package com.etelligens.ecommerce.exception;

public class AddressNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5372735264147848968L;
	
	public AddressNotFoundException(String msg)
	{
		super(msg);
	}

}
