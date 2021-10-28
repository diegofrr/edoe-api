package com.projetodsc.edoe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 30, unique = true)
	private String email;
	
	@Column(nullable = false, length = 11)
	private int celular;
	
	@Column(nullable = false, length = 10)
	private String classe;
	
	@Column(nullable = false, length = 11)
	private int docIdentificacao;
	
	public Usuario() {
		super();
	}
	
	public Usuario(Long id, String nome, String email, int celular, String classe, int doc) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
	}


}
