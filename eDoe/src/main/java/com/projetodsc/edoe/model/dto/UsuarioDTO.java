package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.ClasseUsuario;
import com.projetodsc.edoe.model.TipoUsuario;
import com.projetodsc.edoe.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private int celular;
	private ClasseUsuario classe;
	private int docIdentificacao;
	private TipoUsuario tipo;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome, String email, String senha, int celular, ClasseUsuario classe, int doc) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
	}
	
	public UsuarioDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return new Usuario(this.id, this.nome, this.email, this.senha, this.celular, this.classe, this.docIdentificacao);
	}

	

}
