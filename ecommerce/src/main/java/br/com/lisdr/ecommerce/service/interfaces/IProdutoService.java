package br.com.lisdr.ecommerce.service.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import br.com.lisdr.ecommerce.dto.ProdutoDTO;
import br.com.lisdr.ecommerce.form.ProdutoForm;
import br.com.lisdr.ecommerce.form.UpdateProdutoForm;
import br.com.lisdr.ecommerce.model.Produto;

public interface IProdutoService {
	List<ProdutoDTO> buscarTodos();
	List<ProdutoDTO> buscarPorNome(String value);
	ProdutoDTO buscarPorId(Long id);
	Produto cadastrarProduto(ProdutoForm form);
	void deletarPorId(Long id);
	void deletarTodos();
	ProdutoDTO atualizarPorId(Long id, UpdateProdutoForm form);
}
	
