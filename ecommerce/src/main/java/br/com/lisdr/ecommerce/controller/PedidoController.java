package br.com.lisdr.ecommerce.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lisdr.ecommerce.dao.ClienteDAO;
import br.com.lisdr.ecommerce.dao.PedidoDAO;
import br.com.lisdr.ecommerce.dto.PedidoDTO;
import br.com.lisdr.ecommerce.exception.custom.BadRequestException;
import br.com.lisdr.ecommerce.form.PedidoForm;
import br.com.lisdr.ecommerce.model.Cliente;
import br.com.lisdr.ecommerce.model.Pedido;
import br.com.lisdr.ecommerce.util.NotFoundMessage;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoDAO pedidoDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriBuilder){
		Cliente cliente = clienteDAO.findByNome(pedidoForm.getNomeDoCliente());
		if (cliente != null) {
			Pedido pedido = pedidoForm.converterEmPedido(cliente);
			pedido = pedidoDAO.save(pedido);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(new PedidoDTO(pedido));			
		}
		NotFoundMessage message = new NotFoundMessage(Cliente.getClassName());
		throw new BadRequestException(message.getMessage());
	}
}
