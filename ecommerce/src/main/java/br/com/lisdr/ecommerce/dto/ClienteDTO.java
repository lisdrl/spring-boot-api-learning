package br.com.lisdr.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.lisdr.ecommerce.model.Cliente;
import br.com.lisdr.ecommerce.model.Pedido;

public class ClienteDTO {
	private Long id;
	private String nome;
	private String email;
	private List<PedidoDTO> listaDePedidosDTO;
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		List<PedidoDTO> listaDTO = new ArrayList<>();
		List<Pedido> listaDePedidos = cliente.getListaDePedidos();
		for (Pedido pedido : listaDePedidos) {
			listaDTO.add(new PedidoDTO(pedido));
		}
		this.listaDePedidosDTO = listaDTO;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<PedidoDTO> getListaDePedidosDTO() {
		return listaDePedidosDTO;
	}
}
