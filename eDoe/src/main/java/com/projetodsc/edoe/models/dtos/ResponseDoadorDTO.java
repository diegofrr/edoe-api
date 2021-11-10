package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Usuario;

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
