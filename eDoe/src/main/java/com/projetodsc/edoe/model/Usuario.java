package com.projetodsc.edoe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 30, unique = true)
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
	
	public Usuario(Long id, String nome, String email, String senha, int celular, ClasseUsuario classe, int doc) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
		this.tipo = TipoUsuario.DOADOR;
	}


}
