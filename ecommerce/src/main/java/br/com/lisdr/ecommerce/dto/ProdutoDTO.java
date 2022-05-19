package br.com.lisdr.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.lisdr.ecommerce.model.Produto;

public class ProdutoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double preco;
	
	public ProdutoDTO (Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidade = produto.getQuantidade();
		this.preco = produto.getPreco();
	}
	
	public static List<ProdutoDTO> toDTOList (List<Produto> listaProdutos){
		ArrayList<ProdutoDTO> listaDTOs = new ArrayList<ProdutoDTO>();
		for (Produto produto : listaProdutos) {
			ProdutoDTO novoDTO = new ProdutoDTO(produto);
			listaDTOs.add(novoDTO);
		}
		return listaDTOs;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}
	
	public Double getPreco() {
		return this.preco;
	}
}
