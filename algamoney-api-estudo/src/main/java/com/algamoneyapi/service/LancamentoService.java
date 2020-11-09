package com.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento buscarPeloCodigo(Long codigo) {
		Lancamento lancamentoEncontrado = lancamentoRepository.findByCodigo(codigo);

		if (lancamentoEncontrado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return lancamentoEncontrado;
	}

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoaEncontrada = pessoaRepository.findByCodigo(lancamento.getPessoa().getCodigo());

		if (pessoaEncontrada == null || pessoaEncontrada.isInativa()) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

	public Lancamento alterar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoEncontrado = buscarPeloCodigo(codigo);

		BeanUtils.copyProperties(lancamento, lancamentoEncontrado, "codigo");

		return lancamentoRepository.save(lancamentoEncontrado);
	}

}
