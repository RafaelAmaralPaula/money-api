package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByCodigo(Long codigo);

}
