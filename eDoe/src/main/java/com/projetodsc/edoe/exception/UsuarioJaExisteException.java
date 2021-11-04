package com.projetodsc.edoe.exception;

import lombok.Data;

@Data
public class UsuarioJaExisteException extends RuntimeException {
	
	private String titulo;
	private String detalhe;
	
	public UsuarioJaExisteException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
	public UsuarioJaExisteException() {}

}
