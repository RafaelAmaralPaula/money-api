package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> ,LancamentoRepositoryQuery{
	
	Lancamento findByCodigo(Long codigo);
	
}
