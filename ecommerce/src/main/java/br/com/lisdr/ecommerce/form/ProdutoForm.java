package br.com.lisdr.ecommerce.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lisdr.ecommerce.model.Produto;

public class ProdutoForm {
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @DecimalMin(value = "0")
	private Integer quantidade;
	
	private String descricao;
	
	@NotNull @DecimalMin(value = "0")
	private Double preco;
	
	public ProdutoForm(String nome, Integer quantidade, String descricao, Double preco){
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Produto converterParaProduto() {
		Produto produto = new Produto(nome, quantidade, descricao, preco);
		return produto;
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getPreco() {
		return preco;
	}
}
