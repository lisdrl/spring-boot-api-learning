package br.com.lisdr.ecommerce.dto;

import br.com.lisdr.ecommerce.model.ItemDoPedido;

public class ItemDoPedidoDTO {
	private Long id;
	private Long idDoPedido;
	private String nomeDoCliente;
	private String nomeDoProduto;
	private Integer qtdProduto;
	private Double valorItens;
	
	public ItemDoPedidoDTO(ItemDoPedido itemDoPedido) {
		this.id = itemDoPedido.getId();
		this.idDoPedido = itemDoPedido.getPedido().getId();
		this.nomeDoCliente = itemDoPedido.getPedido().getCliente().getNome();
		this.qtdProduto = itemDoPedido.getQtdProduto();
		this.valorItens = itemDoPedido.getValorDosItens();
	}
	
	public Long getId() {
		return id;
	}
	public Long getIdDoPedido() {
		return idDoPedido;
	}
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public String getNomeDoProduto() {
		return nomeDoProduto;
	}
	public Integer getQtdProduto() {
		return qtdProduto;
	}
	public Double getValorItens() {
		return valorItens;
	}
}
