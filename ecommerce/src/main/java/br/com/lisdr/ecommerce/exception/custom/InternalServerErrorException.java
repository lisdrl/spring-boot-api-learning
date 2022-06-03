package br.com.lisdr.ecommerce.exception.custom;

public class InternalServerErrorException extends RuntimeException {
	
	public InternalServerErrorException (String message) {
		super(message);
	}
}
