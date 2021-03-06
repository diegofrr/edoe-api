package com.apiedoe.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String nome;

	@Id
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private long celular;

	@Column(nullable = false)
	private ClasseUsuario classe;

	@Column(nullable = false)
	private long docIdentificacao;

	@Column(nullable = false)
	private TipoUsuario tipo;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String email, String senha, long celular, ClasseUsuario classe, long docIdentificacao,
			TipoUsuario tipo) {
		this.nome = nome.toUpperCase();
		this.email = email.toUpperCase();
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = docIdentificacao;
		this.tipo = tipo;
	}

}