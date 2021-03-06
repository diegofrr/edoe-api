package com.apiedoe.models.responseModels;

import com.apiedoe.models.ClasseUsuario;
import com.apiedoe.models.Usuario;
import com.apiedoe.models.dtos.UsuarioDTO;

import lombok.Data;

@Data
public class UsuarioResponse {

	private String nome;
	private String email;
	private long celular;
	private ClasseUsuario classe;

	public UsuarioResponse() {}
	
	public UsuarioResponse(UsuarioDTO usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.celular = usuario.getCelular();
		this.classe = usuario.getClasse();
	}

	public UsuarioResponse(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.celular = usuario.getCelular();
		this.classe = usuario.getClasse();
	}

}