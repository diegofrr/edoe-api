package com.projetodsc.edoe.exception;

import lombok.Data;

@Data
public class UsuarioNaoExisteException extends RuntimeException {
	
	private String titulo;
	private String detalhe;

	
	public UsuarioNaoExisteException(String titulo, String detalhe) {
		super();
		this.detalhe = detalhe;
		this.titulo = titulo;
	}
	
}
