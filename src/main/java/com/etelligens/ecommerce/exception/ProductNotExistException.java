package com.etelligens.ecommerce.exception;

public class ProductNotExistException extends RuntimeException  {
 public ProductNotExistException(String msg)
 {
	 super(msg);
 }
}
