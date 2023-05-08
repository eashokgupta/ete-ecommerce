package com.etelligens.ecommerce.exception;

public class ProductNotExistException extends RuntimeException  {
 /**
	 * 
	 */
	private static final long serialVersionUID = -3088731898279118773L;

public ProductNotExistException(String msg)
 {
	 super(msg);
 }
}
