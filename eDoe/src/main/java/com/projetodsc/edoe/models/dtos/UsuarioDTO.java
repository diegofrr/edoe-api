package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.ClasseUsuario;
import com.projetodsc.edoe.models.TipoUsuario;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private int celular;
	private ClasseUsuario classe;
	private int docIdentificacao;
	private TipoUsuario tipo = TipoUsuario.DOADOR;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome, String email, int celular, ClasseUsuario classe, int doc) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
	}

	public Usuario getUsuario() {
		return new Usuario(this.id, this.nome, this.email, this.celular, this.classe, this.docIdentificacao);
	}

}
