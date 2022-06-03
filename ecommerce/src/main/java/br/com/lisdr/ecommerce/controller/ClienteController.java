package br.com.lisdr.ecommerce.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

import br.com.lisdr.ecommerce.dao.ClienteDAO;
import br.com.lisdr.ecommerce.dto.ClienteDTO;
import br.com.lisdr.ecommerce.exception.custom.BadRequestException;
import br.com.lisdr.ecommerce.form.ClienteForm;
import br.com.lisdr.ecommerce.model.Cliente;
import br.com.lisdr.ecommerce.util.NotFoundMessage;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteDAO clienteDAO;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<Cliente> clientes = clienteDAO.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO(cliente));
		}
		return ResponseEntity.ok(clientesDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> optional = clienteDAO.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ClienteDTO(optional.get()));
		}
		return ((BodyBuilder) ResponseEntity.notFound()).body(new NotFoundMessage(Cliente.getClassName()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		if (clienteDAO.findByEmail(form.getEmail()) == null) {
			Cliente cliente = form.converterParaCliente();
			cliente = clienteDAO.save(cliente);
			URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
		}
		throw new BadRequestException("E-mail já cadastrado");
	}
}
