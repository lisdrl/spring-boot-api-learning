package br.com.lisdr.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.lisdr.ecommerce.dao.ProdutoDAO;
import br.com.lisdr.ecommerce.dto.ProdutoDTO;
import br.com.lisdr.ecommerce.exception.custom.ProdutoNotFoundException;
import br.com.lisdr.ecommerce.exception.custom.ResourceNotFoundException;
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProdutoForm;
import br.com.lisdr.ecommerce.model.Produto;
import br.com.lisdr.ecommerce.service.interfaces.IProdutoService;
import br.com.lisdr.ecommerce.util.NotFoundMessage;

@Component
public class ProdutoService implements IProdutoService {
	@Autowired
	ProdutoDAO produtoDAO;

	@Override
	public List<ProdutoDTO> buscarTodos() {
		List<Produto> listaProdutos = produtoDAO.findAll();
		return ProdutoDTO.toDTOList(listaProdutos);
	}

	@Override
	public List<ProdutoDTO> buscarPorNome(String value) {
		String s = "%" + value + "%";
		List<Produto> lista = produtoDAO.findByNomeLike(s);
		if (lista.isEmpty()) {
			throw new ProdutoNotFoundException();
		}
		List<ProdutoDTO> listaDTO = ProdutoDTO.toDTOList(lista);
		return listaDTO;
	}

	@Override
	public ProdutoDTO buscarPorId(Long id) {
		Optional<Produto> produto = produtoDAO.findById(id);
		if(produto.isEmpty()) {
			throw new ProdutoNotFoundException();
		}
		return new ProdutoDTO(produto.get());
	}

	@Override
	public Produto cadastrarProduto(ProdutoForm form) {
		Produto produto = form.converterParaProduto();
		produto = produtoDAO.save(produto);
		return produto;
	}

	@Override
	public void deletarPorId(Long id) {
		Optional<Produto> produto = produtoDAO.findById(id);
		if (produto.isEmpty()) {
			NotFoundMessage message = new NotFoundMessage(Produto.getClassName());
			throw new ResourceNotFoundException(message.getMessage(), HttpStatus.NOT_FOUND);
		}
		produtoDAO.deleteById(id);
	}

	@Override
	public void deletarTodos() {
		produtoDAO.deleteAll();
	}

	@Override
	public ProdutoDTO atualizarPorId(Long id, UpdateProdutoForm form) {
		Optional<Produto> optional = produtoDAO.findById(id);
		if (optional.isEmpty()) {
			NotFoundMessage message = new NotFoundMessage(Produto.getClassName());
			throw new ResourceNotFoundException(message.getMessage(), HttpStatus.NOT_FOUND);
		}
		Produto produto = form.convertToProduct(id, produtoDAO);
		return new ProdutoDTO(produto);
	}
	
	
	
}
