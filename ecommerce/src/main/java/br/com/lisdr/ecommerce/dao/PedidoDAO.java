package br.com.lisdr.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.lisdr.ecommerce.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Long> {
	
}
