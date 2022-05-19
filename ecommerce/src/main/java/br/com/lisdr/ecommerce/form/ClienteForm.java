package br.com.lisdr.ecommerce.form;

import br.com.lisdr.ecommerce.model.Cliente;

public class ClienteForm {
	private String nome;
	private String email;
	
	public ClienteForm(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Cliente converterParaCliente() {
		Cliente novoCliente = new Cliente(nome, email);
		return novoCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
}
