package com.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo , Categoria categoria) {
		Categoria categoriaEncontrada = buscarPeloCodigo(codigo);
		
		BeanUtils.copyProperties(categoria, categoriaEncontrada, "codigo");
		return categoriaRepository.save(categoriaEncontrada);
		
	}

	public Categoria buscarPeloCodigo(Long codigo) {
		Categoria categoriaEncontrada = categoriaRepository.findByCodigo(codigo);
		
		if(categoriaEncontrada == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaEncontrada;
	}
	
}
