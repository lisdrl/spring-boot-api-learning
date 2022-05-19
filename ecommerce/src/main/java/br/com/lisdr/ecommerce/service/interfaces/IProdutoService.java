package br.com.lisdr.ecommerce.service.interfaces;

import java.util.List;

import br.com.lisdr.ecommerce.dto.ProdutoDTO;
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProductForm;
import br.com.lisdr.ecommerce.model.Produto;

public interface IProdutoService {
	List<ProdutoDTO> retornarTodos();
	List<ProdutoDTO> buscarPorNome(String nome);
	ProdutoDTO buscarPorId(Long id);
	Produto cadastrarProduto(ProdutoForm form);
	void deletarPorId(Long id);
	void deletarTodos();
	ProdutoDTO atualizarPorId(Long id, UpdateProductForm form);
}
	
