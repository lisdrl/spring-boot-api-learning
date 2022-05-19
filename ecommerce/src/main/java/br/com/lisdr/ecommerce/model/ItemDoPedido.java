package br.com.lisdr.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto_pedido")
public class ItemDoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	@JsonIgnoreProperties("listaDeItens")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	@JsonIgnoreProperties("listaDePedidos")
	private Produto produto;
	
	@Column(name = "qtd_produto", nullable = false)
	private Integer qtdProduto;
	
	@Column(name = "valor_itens", nullable = false)
	private Double valorDosItens;
	
	public ItemDoPedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(Integer qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public Double getValorDosItens() {
		return valorDosItens;
	}

	public void setValorDosItens(Double valorDosItens) {
		this.valorDosItens = valorDosItens;
	}
}
