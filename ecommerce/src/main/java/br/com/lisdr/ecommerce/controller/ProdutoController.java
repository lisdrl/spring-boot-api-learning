package br.com.lisdr.ecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lisdr.ecommerce.dto.ProdutoDTO;
import br.com.lisdr.ecommerce.exception.custom.InternalServerErrorException;
import br.com.lisdr.ecommerce.exception.custom.ProdutoNotFoundException;
import br.com.lisdr.ecommerce.exception.custom.ResourceNotFoundException;
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProdutoForm;
import br.com.lisdr.ecommerce.model.Produto;
import br.com.lisdr.ecommerce.service.interfaces.IProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private IProdutoService service;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		try {
			List<ProdutoDTO> produtos = service.buscarTodos();
			return ResponseEntity.ok(produtos);
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@GetMapping("/search")
//	nomeProduto
	public ResponseEntity<?> buscarPorNome(@RequestParam(name = "nome") String value) {
		try {
//			produtos
			List<ProdutoDTO> lista = service.buscarPorNome(value);
			return ResponseEntity.ok(lista);
		} catch (ProdutoNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage(), e.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
//		Generics => problema no Swagger, teria que setar na mão
//		Retorno deve ser mais preciso, não indeterminado, até pra auxiliar quem vai ler a docs
//		SEMPRE buscar colocar obj certo como retorno
//		try {
		ProdutoDTO produto = service.buscarPorId(id);
		return ResponseEntity.ok(produto);
//		} catch (ProdutoNotFoundException e) {
//			throw new ResourceNotFoundException(e.getMessage(), e.getStatus());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new InternalServerErrorException(e.getMessage());
//		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody @Valid ProdutoForm form,
			UriComponentsBuilder uriBuilder) {
		Produto produto = service.cadastrarProduto(form);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
		service.deletarPorId(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity<?> deletarTodos() {
		service.deletarTodos();
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarPorId(@PathVariable Long id, @Valid @RequestBody UpdateProdutoForm form) {
		ProdutoDTO produto = service.atualizarPorId(id, form);
		return ResponseEntity.ok(produto);
	}

}
