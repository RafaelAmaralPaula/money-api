package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Pessoa findByCodigo(Long codigo);
}
