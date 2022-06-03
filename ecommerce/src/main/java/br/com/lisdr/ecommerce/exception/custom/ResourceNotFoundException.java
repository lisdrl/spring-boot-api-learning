package br.com.lisdr.ecommerce.exception.custom;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public ResourceNotFoundException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
//	Lombock - não em exceções, mas em Repository e DTO
	
}
