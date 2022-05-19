package br.com.lisdr.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lisdr.ecommerce.model.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long>{
	Cliente findByNome(String nome);
	Cliente findByEmail(String email);
}
