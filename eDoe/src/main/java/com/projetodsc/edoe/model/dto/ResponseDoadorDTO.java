package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Usuario;

import lombok.Data;

@Data
public class ResponseDoadorDTO {
	
	private String nome;
	private String email;
	
	public ResponseDoadorDTO(Usuario doador) {
		this.nome = doador.getNome();
		this.email = doador.getEmail();
	}

}
