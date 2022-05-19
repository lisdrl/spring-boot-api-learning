package br.com.lisdr.ecommerce.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lisdr.ecommerce.model.Cliente;
import br.com.lisdr.ecommerce.model.Pedido;

public class PedidoForm {
	@NotNull @NotEmpty
	private String nomeDoCliente;
	
	@NotNull @DecimalMin(value = "0")
	private Double valorTotal;
	
	public PedidoForm(String nomeDoCliente, Double valorTotal) {
		this.nomeDoCliente = nomeDoCliente;
		this.valorTotal = valorTotal;
	}
	
	public Pedido converterEmPedido(Cliente cliente) {
		Pedido pedido = new Pedido(cliente, this.valorTotal);
		return pedido;
	}
	
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
}
