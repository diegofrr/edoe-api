package com.projetodsc.edoe.exception;

import lombok.Data;

@Data
public class NaoAutorizadoException extends RuntimeException {
	
	private String titulo;
	private String detalhe;

	
	public NaoAutorizadoException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
}
