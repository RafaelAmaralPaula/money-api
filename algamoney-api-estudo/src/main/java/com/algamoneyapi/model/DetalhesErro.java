package com.algamoneyapi.model;

public class DetalhesErro {

	private String mensagemUsuario;

	private String mensagemDesenvolvedor;

	public DetalhesErro(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

}
