package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class DoadorDTOResponse {
	
	private String nome;
	private String email;
	
	public DoadorDTOResponse(Usuario doador) {
		this.nome = doador.getNome();
		this.email = doador.getEmail();
	}

}
