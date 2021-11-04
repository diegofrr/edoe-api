package com.projetodsc.edoe.exception;

import lombok.Data;

@Data
public class NaoAutenticadoException extends RuntimeException {
	
	private String titulo;
	private String detalhe;

	
	public NaoAutenticadoException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
}
