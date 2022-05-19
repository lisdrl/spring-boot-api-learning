package br.com.lisdr.ecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProductForm;
import br.com.lisdr.ecommerce.model.Produto;
import br.com.lisdr.ecommerce.service.interfaces.IProdutoService;
import br.com.lisdr.ecommerce.util.NotFoundMessage;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private IProdutoService service;
	
	@GetMapping
	public ResponseEntity<?> buscarTodos(){
		try {
			List<ProdutoDTO> produtos = service.retornarTodos();
			return ResponseEntity.ok(produtos);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProdutoDTO>> retornarPorNome(@RequestParam(name = "nome") String valor){
		try {
			List<ProdutoDTO> lista = service.buscarPorNome(valor);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> retornarPorId(@PathVariable Long id) {
		try {
			ProdutoDTO produto = service.buscarPorId(id);
			return ResponseEntity.ok(produto);
		} catch (Exception e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(new NotFoundMessage(Produto.getClassName()));
		}	
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
		try {
			Produto produto = service.cadastrarProduto(form); 
			URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri(); 
			return ResponseEntity.created(uri).body(new ProdutoDTO(produto));			
		} catch (Exception e) {
			 return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
		try {
			service.deletarPorId(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(new NotFoundMessage(Produto.getClassName()));
		}
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity<?> deleteAll(){
		try {
			service.deletarTodos();	
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarPeloId(@PathVariable Long id, @Valid @RequestBody UpdateProductForm form){
		try {
			ProdutoDTO produto = service.atualizarPorId(id, form);
			return ResponseEntity.ok(produto);
		} catch (Exception e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(new NotFoundMessage(Produto.getClassName()));			
		}
	}
	
}
