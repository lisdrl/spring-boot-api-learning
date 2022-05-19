package br.com.lisdr.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	@JsonIgnoreProperties("listaDePedidos")
	private Cliente cliente;
	
	@Column(name = "valor_pedido", nullable = false)
	private Double valorTotal;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnoreProperties("pedido")
	private List<ItemDoPedido> listaDeItens;
	
	public Pedido() {
	}
	
	public Pedido(Cliente cliente, Double valorTotal) {
		this.cliente = cliente;
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemDoPedido> getListaDeItens() {
		return listaDeItens;
	}

	public void setListaDeItens(List<ItemDoPedido> listaDeItens) {
		this.listaDeItens = listaDeItens;
	}
}
