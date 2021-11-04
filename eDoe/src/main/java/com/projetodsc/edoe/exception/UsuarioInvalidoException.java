package com.projetodsc.edoe.exception;

import lombok.Data;

@Data
public class UsuarioInvalidoException extends RuntimeException {
	
	private String titulo;
	private String detalhe;

	
	public UsuarioInvalidoException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
}
