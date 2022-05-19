package br.com.lisdr.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import br.com.lisdr.ecommerce.model.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long>{
	List<Produto> findByNome(String nome);
}

//	Produto findById(Long id);