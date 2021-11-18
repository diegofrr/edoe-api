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

	@Column(nullable = false, length = 50)
	private String nome;

	@Id
	@Column(unique = true, nullable = false, length = 30)
	private String email;

	@Column(nullable = false, length = 12)
	private String senha;

	@Column(nullable = false, length = 11)
	private int celular;

	@Column(nullable = false, length = 10)
	private ClasseUsuario classe;

	@Column(nullable = false, length = 11)
	private int docIdentificacao;

	@Column(nullable = false)
	private TipoUsuario tipo;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String email, String senha, int celular, ClasseUsuario classe, int doc,
			TipoUsuario tipo) {
		this.nome = nome.toUpperCase();
		this.email = email.toUpperCase();
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
		this.tipo = tipo;
	}

}