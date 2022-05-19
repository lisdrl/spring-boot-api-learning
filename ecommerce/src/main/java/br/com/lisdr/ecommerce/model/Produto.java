package br.com.lisdr.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_produto", nullable = false)
	private String nome;
	
	@Column(name = "qtd_produto", nullable = false)
	private Integer quantidade;
	
	@Column(name = "descricao_produto")
	private String descricao;
	
	@Column(name = "preco_produto", nullable = false)
	private Double preco;
	
	@OneToMany(mappedBy = "produto")
	@JsonIgnoreProperties("produto")
	private List<ItemDoPedido> listaDePedidos;
	
	public Produto() {
	}
	
	public Produto(String nome, Integer quantidade, String descricao, Double preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public static String getClassName() {
		String className = Produto.class.getSimpleName(); 
        return className;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
