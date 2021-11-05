package com.projetodsc.edoe.exception;

public class NaoAutorizadoException extends RuntimeException {

	private String titulo;
	private String detalhe;

	public NaoAutorizadoException(String titulo, String detalhe) {
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
