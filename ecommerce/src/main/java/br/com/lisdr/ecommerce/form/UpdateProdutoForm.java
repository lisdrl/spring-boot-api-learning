package br.com.lisdr.ecommerce.form;

import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lisdr.ecommerce.dao.ProdutoDAO;
import br.com.lisdr.ecommerce.model.Produto;

public class UpdateProdutoForm {
	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@DecimalMin(value = "0")
	private Integer quantidade;

	@NotNull
	private String descricao;

	@NotNull
	@DecimalMin(value = "0")
	private Double preco;

	public UpdateProdutoForm(String nome, Integer quantidade, String descricao, Double preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Produto convertToProduct(Long id, ProdutoDAO produtoDAO) {
		Optional<Produto> optional = produtoDAO.findById(id);
		Produto product = optional.get();
		product.setNome(nome);
		product.setQuantidade(quantidade);
		product.setDescricao(descricao);
		product.setPreco(preco);
		return product;
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
