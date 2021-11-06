package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.TipoUsuario;

import lombok.Data;

@Data
public class AlteraTipo {
	
	private String email;
	private TipoUsuario novoTipo;
	
	public AlteraTipo() {}
	
	public AlteraTipo(String email, TipoUsuario novoTipo) {
		this.email = email;
		this.novoTipo = novoTipo;
	}
	

}
