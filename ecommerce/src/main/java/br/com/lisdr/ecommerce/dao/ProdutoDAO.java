package br.com.lisdr.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lisdr.ecommerce.model.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long>{
	
//	@Query("SELECT * FROM produto WHERE nome_produto LIKE CONCAT('%',:nome,'%')")
	List<Produto> findByNomeLike(String nome);
//	Param => query :nome
//	psql: ' vs %
	
}