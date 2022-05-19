package br.com.lisdr.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lisdr.ecommerce.dao.ProdutoDAO;
import br.com.lisdr.ecommerce.dto.ProdutoDTO;
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProductForm;
import br.com.lisdr.ecommerce.model.Produto;
import br.com.lisdr.ecommerce.service.interfaces.IProdutoService;

@Component
public class ProdutoService implements IProdutoService {
	@Autowired
	ProdutoDAO produtoDAO;

	@Override
	public List<ProdutoDTO> retornarTodos() {
		List<Produto> listaProdutos = produtoDAO.findAll();
		return ProdutoDTO.toDTOList(listaProdutos);
	}

	@Override
	public List<ProdutoDTO> buscarPorNome(String nome) {
		List<ProdutoDTO> listaDTO = ProdutoDTO.toDTOList(produtoDAO.findByNome(nome));
		return listaDTO;
	}

	@Override
	public ProdutoDTO buscarPorId(Long id) {
		Optional<Produto> produto = produtoDAO.findById(id);
//		if(produto.isPresent()) {
			return new ProdutoDTO(produto.get());
//		}
//		throw Exception e = new Exception();
	}

	@Override
	public Produto cadastrarProduto(ProdutoForm form) {
		Produto produto = form.converterParaProduto();
		produto = produtoDAO.save(produto);
		return produto;
	}

	@Override
	public void deletarPorId(Long id) {
//		Optional<Produto> produto = produtoDAO.findById(id);
//		if (produto.isPresent()) {
			produtoDAO.deleteById(id);
//		}
//			throw Exception ResourceNotFound
	}

	@Override
	public void deletarTodos() {
		produtoDAO.deleteAll();
	}

	@Override
	public ProdutoDTO atualizarPorId(Long id, UpdateProductForm form) {
//		Optional<Produto> optional = produtoDAO.findById(id);
//		if (optional.isPresent()) {
			Produto produto = form.convertToProduct(id, produtoDAO);
			return new ProdutoDTO(produto);
//		}
	}
	
	
	
}
