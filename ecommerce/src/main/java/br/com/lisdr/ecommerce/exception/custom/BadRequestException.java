package br.com.lisdr.ecommerce.exception.custom;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}
}
