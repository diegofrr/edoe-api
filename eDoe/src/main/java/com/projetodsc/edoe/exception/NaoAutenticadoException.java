package com.projetodsc.edoe.exception;

public class NaoAutenticadoException extends RuntimeException {

	private String titulo;
	private String detalhe;

	
	public NaoAutenticadoException(String titulo, String detalhe) {
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
