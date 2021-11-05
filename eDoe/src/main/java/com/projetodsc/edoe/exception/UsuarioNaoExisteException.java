package com.projetodsc.edoe.exception;

public class UsuarioNaoExisteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String detalhe;

	
	public UsuarioNaoExisteException(String titulo, String detalhe) {
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
