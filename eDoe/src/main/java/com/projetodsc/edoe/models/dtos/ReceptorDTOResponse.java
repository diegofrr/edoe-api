package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ReceptorDTOResponse {
	
	private String nome;
	private String email;
	
	public ReceptorDTOResponse(Usuario receptor) {
		this.nome = receptor.getNome();
		this.email = receptor.getEmail();
	}

}
