package com.projetodsc.edoe.exception;

public class UsuarioInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String detalhe;

	public UsuarioInvalidoException(String titulo, String detalhe) {
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
