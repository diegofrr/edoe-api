package com.projetodsc.edoe.exceptions;

public class DoacaoInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String detalhe;

	public DoacaoInvalidaException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getDetalhes() {
		return this.detalhe;
	}
	
}
