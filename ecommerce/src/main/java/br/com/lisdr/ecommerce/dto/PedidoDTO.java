package br.com.lisdr.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.lisdr.ecommerce.model.ItemDoPedido;
import br.com.lisdr.ecommerce.model.Pedido;

public class PedidoDTO {
	private String nomeDoCliente;
	private Double valorTotal;
	private List<ItemDoPedidoDTO> listaDeItens;
	
	public PedidoDTO(Pedido pedido) {
		this.nomeDoCliente = pedido.getCliente().getNome();
		this.valorTotal = pedido.getValorTotal();
		if (pedido.getListaDeItens() != null) {
			List<ItemDoPedido> itensDoPedido = pedido.getListaDeItens();
			List<ItemDoPedidoDTO> itensDoPedidoDTO = new ArrayList<ItemDoPedidoDTO>();
			for (ItemDoPedido item : itensDoPedido) {
				itensDoPedidoDTO.add(new ItemDoPedidoDTO(item));
			}
			this.listaDeItens = itensDoPedidoDTO;			
		}
	}
	
	public String getNomeDoCliente() {
		return nomeDoCliente;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public List<ItemDoPedidoDTO> getListaDeItens() {
		return listaDeItens;
	}
}
