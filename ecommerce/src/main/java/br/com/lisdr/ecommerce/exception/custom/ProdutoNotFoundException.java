package br.com.lisdr.ecommerce.exception.custom;

import org.springframework.http.HttpStatus;

public class ProdutoNotFoundException extends ResourceNotFoundException {

	public ProdutoNotFoundException() {
		super("Não foi encontrado nenhum produto.", HttpStatus.NOT_FOUND);
	}
	
}
