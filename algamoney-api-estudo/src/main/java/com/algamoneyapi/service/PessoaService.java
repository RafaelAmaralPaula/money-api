package com.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo , Pessoa pessoa) {
		Pessoa pessoaEncontrada = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaEncontrada, "codigo");
		return pessoaRepository.save(pessoaEncontrada);
		
	}

	public Pessoa buscarPeloCodigo(Long codigo) {
		Pessoa pessoaEncontrada = pessoaRepository.findByCodigo(codigo);
		
		if(pessoaEncontrada == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaEncontrada;
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaEncontrada = buscarPeloCodigo(codigo);
		pessoaEncontrada.setAtivo(ativo);
		pessoaRepository.save(pessoaEncontrada);
		
	}
	
}
