package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.ClasseUsuario;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
	
	private String nome;
	private String email;
	private int celular;
	private ClasseUsuario classe;

	
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
